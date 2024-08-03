package io.github.psycotrompus.sql;

public class SqlOrder implements PartialSql {

	private final SqlColumn column;

	private final boolean desc;

	SqlOrder(SqlColumn column, boolean desc) {
		this.column = column;
		this.desc = desc;
	}

	public String toSql() {
		return String.format("%s %s", column.toSql(), desc ? "DESC" : "ASC");
	}
}
