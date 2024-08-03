package io.github.psycotrompus.sql;

/**
 * Represents a column filter.
 * Generated SQL:
 * <pre>
 *   table1.column1 = table2.column2
 * </pre>
 *
 * @author ejlay
 */
public class SqlColumnFilter implements SqlTypeFilter {

	private final SqlColumn leftOperand;

	private final String comparator;

	private final SqlColumn rightOperand;

	/**
	 * <p>Constructor for SqlColumnFilter.</p>
	 *
	 * @param leftOperand a {@link io.github.psycotrompus.sql.SqlColumn} object
	 * @param comparator a {@link java.lang.String} object
	 * @param rightOperand a {@link io.github.psycotrompus.sql.SqlColumn} object
	 */
	public SqlColumnFilter(SqlColumn leftOperand, String comparator, SqlColumn rightOperand) {
		this.leftOperand = leftOperand;
		this.comparator = comparator;
		this.rightOperand = rightOperand;
	}

	/** {@inheritDoc} */
	@Override
	public String toSql() {
		return String.format("%s %s %s", leftOperand.toSql(), comparator, rightOperand.toSql());
	}
}
