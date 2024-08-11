package io.github.psycotrompus.sql;

/**
 * Build the <code>WHERE</code> clause of a SQL query.
 *
 * @author ejlayco
 */
public interface WhereStep extends SortingStep, LimitStep, FinalStep {

	/**
	 * Adds a {@link SqlTypeFilter} instance to add as a filter to this statement using
	 * the <code>AND</code> logical operator.
	 *
	 * @param filter A {@link SqlTypeFilter} instance to add to this clause.
	 * @return The {@link WhereStep} builder.
	 */
	WhereStep and(SqlTypeFilter filter);

	/**
	 * Adds a {@link SqlTypeFilter} instance to add as a filter to this statement using
	 * the <code>OR</code> logical operator.
	 *
	 * @param filter A {@link SqlTypeFilter} instance to add to this clause.
	 * @return The {@link WhereStep} builder.
	 */
	WhereStep or(SqlTypeFilter filter);
}
