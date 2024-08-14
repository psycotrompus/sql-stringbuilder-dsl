package io.github.psycotrompus.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class SqlBuilderContext implements FinalStep {

	// for SELECT clause
	private final SqlProjection projection;

	// for FROM clause
	private SqlTable rootTable;

	// for JOIN clause
	private final List<SqlJoinBuilder> joins = new ArrayList<>();

	// for WHERE clause
	private final List<SqlTypeFilter> filters = new ArrayList<>();

	// for GROUP BY ... HAVING clause
	private SqlAggregateBuilder aggregate;

	// for ORDER BY clause
	private final List<SqlOrder> orders = new ArrayList<>();

	// for LIMIT clause
	private SqlLimitBuilder limit;

	SqlBuilderContext(SqlProjection projection) {
		this.projection = projection;
	}

	void setRootTable(SqlTable rootTable) {
		this.rootTable = rootTable;
	}

	void addJoin(SqlJoinBuilder join) {
		joins.add(join);
	}

	void addFilter(SqlTypeFilter filter) {
		filters.add(filter);
	}

	void setAggregate(SqlAggregateBuilder aggregate) {
		this.aggregate = aggregate;
	}

	void addOrders(SqlOrder... orders) {
    this.orders.addAll(Arrays.asList(orders));
	}

	void addLimit(SqlLimitBuilder limit) {
		this.limit = limit;
	}

	@Override
	public String build() {
		if (projection == null) {
			throw new SqlBuilderException("No projections specified");
		}
		if (this.rootTable == null) {
			throw new SqlBuilderException("No tables to select from.");
		}
		// SELECT clause
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append(projection.toSql());

		// FROM (and JOIN) clause
		sql.append(" FROM ").append(rootTable.toSql());
		if (!joins.isEmpty()) {
			sql.append(" ")
					.append(joins.stream().map(SqlJoinBuilder::toSql).collect(Collectors.joining(" ")));
		}

		// WHERE clause
		if (!this.filters.isEmpty()) {
			sql.append(" WHERE 1=1 AND ");
			sql.append(filters.stream().map(SqlTypeFilter::toSql).collect(joining(" AND ")));
		}

		// GROUP BY ... HAVING
		if (this.aggregate != null) {
			sql.append(" ").append(aggregate.toSql());
		}

		// ORDER clause
		if (!orders.isEmpty()) {
			sql.append(" ORDER BY ")
					.append(orders.stream().map(SqlOrder::toSql).collect(joining(", ")));
		}

		// LIMIT clause
		if (this.limit != null) {
			sql.append(limit.toSql());
		}

		return sql.append(";").toString();
	}
}
