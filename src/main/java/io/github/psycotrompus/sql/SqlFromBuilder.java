package io.github.psycotrompus.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates all the components in a FROM SQL clause.
 *
 * @author ejlay
 */
public class SqlFromBuilder implements PartialSql {

	private final SqlBuilder sqlBuilder;

	private final SqlTable rootTable;

	private final List<SqlJoinBuilder> tables = new ArrayList<>();

	/**
	 * <p>Constructor for SqlFromBuilder.</p>
	 *
	 * @param sqlBuilder a {@link io.github.psycotrompus.sql.SqlBuilder} object
	 * @param rootTable a {@link io.github.psycotrompus.sql.SqlTable} object
	 */
	public SqlFromBuilder(SqlBuilder sqlBuilder, SqlTable rootTable) {
		this.sqlBuilder = sqlBuilder;
		this.rootTable = rootTable;
	}

	/**
	 * <p>leftJoin.</p>
	 *
	 * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
	 * @return a {@link io.github.psycotrompus.sql.SqlJoinBuilder} object
	 */
	public SqlJoinBuilder leftJoin(SqlTable table) {
		SqlJoinBuilder join = new SqlJoinBuilder(this, "LEFT JOIN", table);
		tables.add(join);
		return join;
	}

	/**
	 * <p>innerJoin.</p>
	 *
	 * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
	 * @return a {@link io.github.psycotrompus.sql.SqlJoinBuilder} object
	 */
	public SqlJoinBuilder innerJoin(SqlTable table) {
		SqlJoinBuilder join = new SqlJoinBuilder(this, "INNER JOIN", table);
		tables.add(join);
		return join;
	}

	/**
	 * <p>where.</p>
	 *
	 * @param filter a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 * @return a {@link io.github.psycotrompus.sql.SqlBuilder} object
	 */
	public SqlBuilder where(SqlTypeFilter filter) {
		return sqlBuilder.where(filter);
	}

	/** {@inheritDoc} */
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
