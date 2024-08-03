package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

public class SqlJoinBuilder implements PartialSql {

	private final SqlFromBuilder fromBuilder;

	private final String joinType;

	private final SqlTable table;

	private SqlTypeFilter filter;

	public SqlJoinBuilder(SqlFromBuilder fromBuilder, String joinType, SqlTable table) {
		this.fromBuilder = fromBuilder;
		this.joinType = joinType;
		this.table = table;
	}

	public SqlFromBuilder on(SqlTypeFilter filter) {
		this.filter = filter;
		return fromBuilder;
	}

	@Override
	public String toSql() {
		if (isBlank(table.getAlias())) {
			throw new RuntimeException("Table alias is required.");
		}
		return String.format("%s %s ON %s", joinType, table.toSql(), filter.toSql());
	}
}
