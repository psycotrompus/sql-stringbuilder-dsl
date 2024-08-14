package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

/**
 * Represents a column definition when generating SQL queries. To generate a column, use the method
 * {@link SqlTable#column(String)}.
 *
 * @author ejlayco
 */
public class SqlColumn extends PartialSql implements Projection {

	private final SqlTable table;

	private final String name;

	private String alias;

	/**
	 * <p>Constructor for SqlColumn.</p>
	 *
	 * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
	 * @param name a {@link java.lang.String} object
	 */
	SqlColumn(SqlTable table, String name) {
		this(table, name, null);
	}

	/**
	 * <p>Constructor for SqlColumn.</p>
	 *
	 * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
	 * @param name a {@link java.lang.String} object
	 * @param alias a {@link java.lang.String} object
	 */
	SqlColumn(SqlTable table, String name, String alias) {
		this.table = table;
		this.name = name;
		this.alias = alias;
	}

	/**
	 * Updates this column definition with alias.
	 *
	 * @param alias The new alias of type {@link String} for this column.
	 * @return AAn updated {@link SqlColumn} with the new alias.
	 */
	@Override
	public SqlColumn as(String alias) {
		this.alias = alias;
		return this;
	}

	/**
	 * SQL predicate/filter that checks if this column is equal to the parameter value.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter eq(String parameter) {
		return new SqlValueFilter(this, "=", ":" + parameter);
	}

	/**
	 * Creates the predicate/filter if this column's value is present in this parameter.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter in(String parameter) {
		return new SqlValueFilter(this, "IN", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is <code>true</code>.
	 *
	 * @return a {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter isTrue() {
		return new SqlValueFilter(this, "IS", "true");
	}

	/**
	 * Adds a filter if this column's value is <code>false</code>.
	 *
	 * @return a {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter isFalse() {
		return new SqlValueFilter(this, "IS", "false");
	}

	/**
	 * Adds a filter if this column's value is <code>NULL</code>.
	 *
	 * @return a {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter isNull() {
		return new SqlValueFilter(this, "IS", "null");
	}

	/**
	 * Adds a filter if this column's value matches the parameter value using the
	 * <code>LIKE</code> operator.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter like(String parameter) {
		return new SqlValueFilter(this, "LIKE", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is <strong>greater than</strong> the parameter value.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter gt(String parameter) {
		return new SqlValueFilter(this, ">", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is <b>less than</b> the parameter value.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter lt(String parameter) {
		return new SqlValueFilter(this, "<", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is <strong>greater than or equal to</strong>
	 * the parameter value.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter gte(String parameter) {
		return new SqlValueFilter(this, ">=", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is <b>less than or equal to</b> the parameter value.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter lte(String parameter) {
		return new SqlValueFilter(this, "<=", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is <b>NOT equal to</b> the parameter value.
	 *
	 * @param parameter Name of this parameter.
	 * @return A {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter ne(String parameter) {
		return new SqlValueFilter(this, "<>", ":" + parameter);
	}

	/**
	 * Adds a filter if this column's value is equal to another column value.
	 *
	 * @param column The {@link SqlColumn} object that will equate to this column
	 * @return a {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter eq(SqlColumn column) {
		return new SqlColumnFilter(this, "=", column);
	}

	/**
	 * Adds a filter if this column's value is NOT equal to another column value.
	 *
	 * @param column The {@link SqlColumn} object that will equate to this column
	 * @return a {@link SqlTypeFilter} object that represents the partial SQL statement.
	 */
	public SqlTypeFilter ne(SqlColumn column) {
		return new SqlColumnFilter(this, "<>", column);
	}

	/**
	 * Creates a partial SQL statement that returns the result in ascending order using this column
	 *
	 * @return A {@link SqlOrder} object that represents the partial SQL statement.
	 */
	public SqlOrder asc() {
		return new SqlOrder(this, false);
	}

	/**
	 * Creates a partial SQL statement that returns the result in descending order using this column
	 *
	 * @return A {@link SqlOrder} object that represents the partial SQL statement.
	 */
	public SqlOrder desc() {
		return new SqlOrder(this, true);
	}

	SqlTable getTable() {
		return this.table;
	}

	protected String getName() {
		return this.name;
	}

	protected String getAlias() {
		return this.alias;
	}

	@Override
	public String project() {
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

	/** {@inheritDoc} */
	@Override
	String toSql() {
		if (!isBlank(alias)) {
			return alias;
		}
		if (!isBlank(table.getAlias())) {
			return table.getAlias() + "." + name;
		}
		return name;
	}
}
