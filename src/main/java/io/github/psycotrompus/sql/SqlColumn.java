package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

/**
 * Represents a column definition when generating SQL queries.
 *
 * @author ejlay
 */
public class SqlColumn implements PartialSql {

	private final SqlTable table;

	private final String name;

	private final String alias;

	/**
	 * <p>Constructor for SqlColumn.</p>
	 *
	 * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
	 * @param name a {@link java.lang.String} object
	 */
	public SqlColumn(SqlTable table, String name) {
		this(table, name, null);
	}

	/**
	 * <p>Constructor for SqlColumn.</p>
	 *
	 * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
	 * @param name a {@link java.lang.String} object
	 * @param alias a {@link java.lang.String} object
	 */
	public SqlColumn(SqlTable table, String name, String alias) {
		this.table = table;
		this.name = name;
		this.alias = alias;
	}

	/**
	 * <p>eq.</p>
	 *
	 * @param parameter a {@link java.lang.String} object
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter eq(String parameter) {
		return new SqlValueFilter(this, "=", ":" + parameter);
	}

	/**
	 * <p>in.</p>
	 *
	 * @param parameter a {@link java.lang.String} object
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter in(String parameter) {
		return new SqlValueFilter(this, "IN", ":" + parameter);
	}

	/**
	 * <p>isTrue.</p>
	 *
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter isTrue() {
		return new SqlValueFilter(this, "IS", "true");
	}

	/**
	 * <p>isFalse.</p>
	 *
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter isFalse() {
		return new SqlValueFilter(this, "IS", "false");
	}

	/**
	 * <p>isNull.</p>
	 *
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter isNull() {
		return new SqlValueFilter(this, "IS", "null");
	}

	/**
	 * <p>like.</p>
	 *
	 * @param parameter a {@link java.lang.String} object
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter like(String parameter) {
		return new SqlValueFilter(this, "LIKE", ":" + parameter);
	}

	/**
	 * <p>eq.</p>
	 *
	 * @param column a {@link io.github.psycotrompus.sql.SqlColumn} object
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	public SqlTypeFilter eq(SqlColumn column) {
		return new SqlColumnFilter(this, "=", column);
	}

	/**
	 * <p>asc.</p>
	 *
	 * @return a {@link io.github.psycotrompus.sql.SqlOrder} object
	 */
	public SqlOrder asc() {
		return new SqlOrder(this, false);
	}

	/**
	 * <p>desc.</p>
	 *
	 * @return a {@link io.github.psycotrompus.sql.SqlOrder} object
	 */
	public SqlOrder desc() {
		return new SqlOrder(this, true);
	}

	/** {@inheritDoc} */
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
