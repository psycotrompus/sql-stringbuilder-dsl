package io.github.psycotrompus.sql;

import java.util.ArrayList;
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

	// for ORDER BY clause
	private final List<SqlOrder> orders = new ArrayList<>();

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

	void addOrder(SqlOrder order) {
		orders.add(order);
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
			sql.append(" ");
			sql.append(joins.stream().map(SqlJoinBuilder::toSql).collect(Collectors.joining(" ")));
		}

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
