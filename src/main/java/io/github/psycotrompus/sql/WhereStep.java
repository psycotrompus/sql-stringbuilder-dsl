package io.github.psycotrompus.sql;

/**
 * Build the <code>WHERE</code> clause of a SQL query.
 *
 * @author ejlayco
 */
public interface WhereStep extends SortingStep, LimitStep, FinalStep {

	WhereStep and(SqlTypeFilter filter);

	WhereStep or(SqlTypeFilter filter);
}
