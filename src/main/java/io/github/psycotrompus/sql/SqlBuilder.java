package io.github.psycotrompus.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public class SqlBuilder implements PartialSql {

	public static SqlBuilder select(SqlColumn... columns) {
		return select(new SqlProjection(asList(columns)));
	}

	public static SqlBuilder select(SqlProjection projection) {
		return new SqlBuilder(projection);
	}

	private final SqlProjection projection;

	private SqlFromBuilder fromBuilder;

	private final List<SqlTypeFilter> filters = new ArrayList<>();

	private final List<SqlOrder> orders = new ArrayList<>();

	SqlBuilder(SqlProjection projection) {
		this.projection = projection;
	}

	public SqlFromBuilder from(SqlTable table) {
		return (this.fromBuilder = new SqlFromBuilder(this, table));
	}

	public SqlBuilder where(SqlTypeFilter filter) {
		this.filters.add(filter);
		return this;
	}

	public SqlBuilder and(SqlTypeFilter filter) {
		this.filters.add(filter);
		return this;
	}

	public SqlBuilder orderBy(SqlOrder... orders) {
		this.orders.addAll(Arrays.asList(orders));
		return this;
	}

	@Override
	public String toSql() {
		if (projection == null) {
			throw new SqlBuilderException("No projections specified");
		}
		if (this.fromBuilder == null) {
			throw new SqlBuilderException("No tables to select from.");
		}
		// SELECT clause
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append(projection.toSql());

		// FROM (and JOIN) clause
		sql.append(" FROM ").append(fromBuilder.toSql());

		// WHERE clause
		if (!this.filters.isEmpty()) {
			sql.append(" WHERE 1=1 AND ");
			sql.append(filters.stream().map(SqlTypeFilter::toSql).collect(joining(" AND ")));
		}

		// ORDER clause
		if (!orders.isEmpty()) {
			sql.append(" ORDER BY ");
			sql.append(orders.stream().map(SqlOrder::toSql).collect(joining(", ")));
		}

		// TODO: Implement LIMIT clause

		return sql.append(";").toString();
	}
}
