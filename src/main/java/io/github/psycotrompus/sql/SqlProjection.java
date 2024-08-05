package io.github.psycotrompus.sql;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Represents the columns as part of the <code>SELECT</code> clause.
 *
 * @author ejlayco
 */
public class SqlProjection extends PartialSql {

	private final List<SqlColumn> columns;

	/**
	 * <p>Constructor for SqlProjection.</p>
	 *
	 * @param columns a {@link java.util.List} object
	 */
	public SqlProjection(List<SqlColumn> columns) {
		this.columns = columns;
	}

	/** {@inheritDoc} */
	@Override
	String toSql() {
		return columns.stream().map(SqlColumn::toSql).collect(joining(", "));
	}
}
