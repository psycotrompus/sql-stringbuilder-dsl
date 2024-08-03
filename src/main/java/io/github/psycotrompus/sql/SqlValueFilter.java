package io.github.psycotrompus.sql;

/**
 * References a parameterized value filter in a SQL statement.
 * The format is:
 * <pre>
 *   table_alias.column_name [comparator] parameter
 * </pre>
 *
 * Sample output:
 * <pre>
 *   table.column_name = :parameter
 * </pre>
 * OR
 * <pre>
 *   table.column_name IS true
 * </pre>
 */
public class SqlValueFilter implements SqlTypeFilter {

	private final SqlColumn column;

	private final String comparator;

	private final String parameter;

	SqlValueFilter(SqlColumn column, String comparator, String parameter) {
		this.column = column;
		this.comparator = comparator;
		this.parameter = parameter;
	}

	@Override
	public String toSql() {
		return String.format("%s %s %s", column.toSql(), comparator, parameter);
	}
}
