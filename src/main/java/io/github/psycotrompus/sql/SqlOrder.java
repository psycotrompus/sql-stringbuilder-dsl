package io.github.psycotrompus.sql;

/**
 * Represents a column as part of the <code>ORDER BY</code> clause.
 *
 * @author ejlay
 */
public class SqlOrder implements PartialSql {

	private final SqlColumn column;

	private final boolean desc;

	SqlOrder(SqlColumn column, boolean desc) {
		this.column = column;
		this.desc = desc;
	}

	/**
	 * <p>toSql.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String toSql() {
		return String.format("%s %s", column.toSql(), desc ? "DESC" : "ASC");
	}
}
