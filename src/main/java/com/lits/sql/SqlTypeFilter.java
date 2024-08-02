package com.lits.sql;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public interface SqlTypeFilter extends PartialSql {

	default SqlGroupedFilter and(SqlTypeFilter filter) {
		if (filter instanceof SqlGroupedFilter groupedFilter) {
			List<SqlTypeFilter> filters = new ArrayList<>(groupedFilter.getFilters());
			filters.add(filter);
			return new SqlGroupedFilter("AND", filters);
		}
		return new SqlGroupedFilter("AND", asList(this, filter));
	}

	default SqlTypeFilter or(SqlTypeFilter filter) {
		if (filter instanceof SqlGroupedFilter groupedFilter) {
			List<SqlTypeFilter> filters = new ArrayList<>(groupedFilter.getFilters());
			filters.add(filter);
			return new SqlGroupedFilter("OR", filters);
		}
		return new SqlGroupedFilter("OR", asList(this, filter));
	}
}
