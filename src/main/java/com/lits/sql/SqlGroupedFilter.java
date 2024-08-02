package com.lits.sql;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class SqlGroupedFilter implements SqlTypeFilter {

	private final String operator;

	private final List<SqlTypeFilter> filters;

	SqlGroupedFilter(String operator, List<SqlTypeFilter> filters) {
		this.operator = operator;
		this.filters = filters;
	}

	public List<SqlTypeFilter> getFilters() {
		return filters;
	}

	@Override
	public String toSql() {
		return "(" + filters.stream().map(SqlTypeFilter::toSql)
				.collect(joining(" " + operator + " ")) + ")";
	}
}
