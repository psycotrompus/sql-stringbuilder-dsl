package io.github.psycotrompus.sql;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * The base class for different types of filters in the <code>WHERE</code> clause.
 *
 * @author ejlay
 */
public interface SqlTypeFilter extends PartialSql {

	/**
	 * <p>and.</p>
	 *
	 * @param filter a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 * @return a {@link io.github.psycotrompus.sql.SqlGroupedFilter} object
	 */
	default SqlGroupedFilter and(SqlTypeFilter filter) {
		if (filter instanceof SqlGroupedFilter groupedFilter) {
			List<SqlTypeFilter> filters = new ArrayList<>(groupedFilter.getFilters());
			filters.add(filter);
			return new SqlGroupedFilter("AND", filters);
		}
		return new SqlGroupedFilter("AND", asList(this, filter));
	}

	/**
	 * <p>or.</p>
	 *
	 * @param filter a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 * @return a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
	 */
	default SqlTypeFilter or(SqlTypeFilter filter) {
		if (filter instanceof SqlGroupedFilter groupedFilter) {
			List<SqlTypeFilter> filters = new ArrayList<>(groupedFilter.getFilters());
			filters.add(filter);
			return new SqlGroupedFilter("OR", filters);
		}
		return new SqlGroupedFilter("OR", asList(this, filter));
	}
}
