package io.github.psycotrompus.sql;

import javax.annotation.Nullable;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;
import static java.util.Arrays.asList;

/**
 * Represents a table definition when generating SQL queries.
 */
public class SqlTable implements PartialSql {

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

	@Nullable
	public String getAlias() {
		return alias;
	}

	public SqlProjection asterisk() {
		return new SqlProjection(asList(new SqlColumn(this, "*")));
	}

	public SqlColumn column(String name) {
		return new SqlColumn(this, name);
	}

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
