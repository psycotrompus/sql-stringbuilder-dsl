package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

/**
 * Represents a column definition when generating SQL queries.
 */
public class SqlColumn implements PartialSql {

	private final SqlTable table;

	private final String name;

	private final String alias;

	public SqlColumn(SqlTable table, String name) {
		this(table, name, null);
	}

	public SqlColumn(SqlTable table, String name, String alias) {
		this.table = table;
		this.name = name;
		this.alias = alias;
	}

	public SqlTypeFilter eq(String parameter) {
		return new SqlValueFilter(this, "=", ":" + parameter);
	}

	public SqlTypeFilter in(String parameter) {
		return new SqlValueFilter(this, "IN", ":" + parameter);
	}

	public SqlTypeFilter isTrue() {
		return new SqlValueFilter(this, "IS", "true");
	}

	public SqlTypeFilter isFalse() {
		return new SqlValueFilter(this, "IS", "false");
	}

	public SqlTypeFilter isNull() {
		return new SqlValueFilter(this, "IS", "null");
	}

	public SqlTypeFilter like(String parameter) {
		return new SqlValueFilter(this, "LIKE", ":" + parameter);
	}

	public SqlTypeFilter eq(SqlColumn column) {
		return new SqlColumnFilter(this, "=", column);
	}

	public SqlOrder asc() {
		return new SqlOrder(this, false);
	}

	public SqlOrder desc() {
		return new SqlOrder(this, true);
	}

	@Override
	public String toSql() {
		if (!isBlank(table.getAlias()) && !isBlank(alias)) {
			return String.format("%s.%s AS %s", table.getAlias(), name, alias);
		}
		if (isBlank(table.getAlias()) && !isBlank(alias)) {
			return String.format("%s AS %s", name, alias);
		}
		if (!isBlank(table.getAlias()) && isBlank(alias)) {
			return String.format("%s.%s", table.getAlias(), name);
		}
		return name;
	}
}
