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
 *
 * @author ejlayco
 */
public class SqlValueFilter extends SqlTypeFilter {

	private final SqlColumn column;

	private final String comparator;

	private final String parameter;

	SqlValueFilter(SqlColumn column, String comparator, String parameter) {
		this.column = column;
		this.comparator = comparator;
		this.parameter = parameter;
	}

	/** {@inheritDoc} */
	@Override
	String toSql() {
		return String.format("%s %s %s", column.toSql(), comparator, parameter);
	}
}
