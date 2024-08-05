package io.github.psycotrompus.sql;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * The base class for different types of filters in the <code>WHERE</code> clause.
 *
 * @author ejlayco
 */
public abstract class SqlTypeFilter extends PartialSql {

	/**
	 * Combines filters with an <code>AND</code> operator.
	 *
	 * @param filter The {@link SqlTypeFilter} instance to combine with this filter.
	 * @return A combined filer in a {@link SqlGroupedFilter} instance.
	 */
	SqlGroupedFilter and(SqlTypeFilter filter) {
		if (filter instanceof SqlGroupedFilter groupedFilter) {
			List<SqlTypeFilter> filters = new ArrayList<>(groupedFilter.getFilters());
			filters.add(filter);
			return new SqlGroupedFilter("AND", filters);
		}
		return new SqlGroupedFilter("AND", asList(this, filter));
	}

	/**
	 * Combines filters with an <code>OR</code> operator.
	 *
	 * @param filter The {@link SqlTypeFilter} instance to combine with this filter.
	 * @return A combined filer in a {@link SqlGroupedFilter} instance.
	 */
	SqlTypeFilter or(SqlTypeFilter filter) {
		if (filter instanceof SqlGroupedFilter groupedFilter) {
			List<SqlTypeFilter> filters = new ArrayList<>(groupedFilter.getFilters());
			filters.add(filter);
			return new SqlGroupedFilter("OR", filters);
		}
		return new SqlGroupedFilter("OR", asList(this, filter));
	}
}
