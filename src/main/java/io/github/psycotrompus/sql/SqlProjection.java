package io.github.psycotrompus.sql;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Represents the columns as part of the <code>SELECT</code> clause.
 */
public class SqlProjection implements PartialSql {

	private final List<SqlColumn> columns;

	public SqlProjection(List<SqlColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toSql() {
		return columns.stream().map(SqlColumn::toSql).collect(joining(", "));
	}
}
