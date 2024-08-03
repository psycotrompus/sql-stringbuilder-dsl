package io.github.psycotrompus.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates all the components in a FROM SQL clause.
 */
public class SqlFromBuilder implements PartialSql {

	private final SqlBuilder sqlBuilder;

	private final SqlTable rootTable;

	private final List<SqlJoinBuilder> tables = new ArrayList<>();

	public SqlFromBuilder(SqlBuilder sqlBuilder, SqlTable rootTable) {
		this.sqlBuilder = sqlBuilder;
		this.rootTable = rootTable;
	}

	public SqlJoinBuilder leftJoin(SqlTable table) {
		SqlJoinBuilder join = new SqlJoinBuilder(this, "LEFT JOIN", table);
		tables.add(join);
		return join;
	}

	public SqlJoinBuilder innerJoin(SqlTable table) {
		SqlJoinBuilder join = new SqlJoinBuilder(this, "INNER JOIN", table);
		tables.add(join);
		return join;
	}

	public SqlBuilder where(SqlTypeFilter filter) {
		return sqlBuilder.where(filter);
	}

	@Override
	public String toSql() {
		StringBuilder sql = new StringBuilder(rootTable.toSql());
		if (!tables.isEmpty()) {
			sql.append(" ");
			sql.append(tables.stream().map(SqlJoinBuilder::toSql).collect(Collectors.joining(" ")));
		}
		return sql.toString();
	}
}
