package io.github.psycotrompus.sql;

import javax.annotation.Nullable;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;
import static java.util.Arrays.asList;

/**
 * Represents a table definition when generating SQL queries.
 *
 * @author ejlay
 */
public class SqlTable implements PartialSql {

	/**
	 * <p>of.</p>
	 *
	 * @param table a {@link java.lang.String} object
	 * @return a {@link io.github.psycotrompus.sql.SqlTable.Builder} object
	 */
	public static Builder of(String table) {
		return new Builder(table);
	}

	public static class Builder {

		private String schema;

		private final String table;

		private String alias;

		public Builder(String table) {
			this.table = table;
		}

		public Builder on(String schema) {
			this.schema = schema;
			return this;
		}

		public Builder as(String alias) {
			this.alias = alias;
			return this;
		}

		public SqlTable build() {
			return new SqlTable(schema, table, alias);
		}
	}

	private final String schema;
	private final String table;
	private final String alias;

	private SqlTable(String schema, String table, String alias) {
		this.schema = schema;
		this.table = table;
		this.alias = alias;
	}

	/**
	 * <p>Getter for the field <code>alias</code>.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	@Nullable
	public String getAlias() {
		return alias;
	}

	/**
	 * <p>asterisk.</p>
	 *
	 * @return a {@link io.github.psycotrompus.sql.SqlProjection} object
	 */
	public SqlProjection asterisk() {
		return new SqlProjection(asList(new SqlColumn(this, "*")));
	}

	/**
	 * <p>column.</p>
	 *
	 * @param name a {@link java.lang.String} object
	 * @return a {@link io.github.psycotrompus.sql.SqlColumn} object
	 */
	public SqlColumn column(String name) {
		return new SqlColumn(this, name);
	}

	/** {@inheritDoc} */
	@Override
	public String toSql() {
		if (!isBlank(schema) && !isBlank(alias)) {
			return String.format("%s.%s AS %s", schema, table, alias);
		}
		if (!isBlank(schema) && isBlank(alias)) {
			return String.format("%s.%s", schema, table);
		}
		if (isBlank(schema) && !isBlank(alias)) {
			return String.format("%s AS %s", table, alias);
		}
		return table;
	}
}
