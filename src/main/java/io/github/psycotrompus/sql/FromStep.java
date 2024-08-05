package io.github.psycotrompus.sql;

/**
 * Builds the <code>FROM</code> clause.
 *
 * @author ejlayco
 */
public interface FromStep extends SortingStep, FinalStep {

	JoinStep leftJoin(SqlTable table);

	JoinStep innerJoin(SqlTable table);

	WhereStep where(SqlTypeFilter filter);
}
