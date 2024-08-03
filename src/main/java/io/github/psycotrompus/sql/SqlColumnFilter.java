package io.github.psycotrompus.sql;

/**
 * Represents a column filter.
 * Generated SQL:
 * <pre>
 *   table1.column1 = table2.column2
 * </pre>
 */
public class SqlColumnFilter implements SqlTypeFilter {

	private final SqlColumn leftOperand;

	private final String comparator;

	private final SqlColumn rightOperand;

	public SqlColumnFilter(SqlColumn leftOperand, String comparator, SqlColumn rightOperand) {
		this.leftOperand = leftOperand;
		this.comparator = comparator;
		this.rightOperand = rightOperand;
	}

	@Override
	public String toSql() {
		return String.format("%s %s %s", leftOperand.toSql(), comparator, rightOperand.toSql());
	}
}
