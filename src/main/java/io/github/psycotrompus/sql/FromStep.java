package io.github.psycotrompus.sql;

/**
 * Builds the <code>FROM</code> clause.
 *
 * @author ejlayco
 */
public interface FromStep extends SortingStep, LimitStep, FinalStep {

	/**
	 * Starts a <code>LEFT JOIN</code> builder step.
	 *
	 * @param table A {@link SqlTable} instance to join to this step.
	 * @return An instance of {@link JoinStep} builder.
	 */
	JoinStep leftJoin(SqlTable table);

	/**
	 * Starts a <code>INNER JOIN</code> builder step.
	 *
	 * @param table A {@link SqlTable} instance to join to this stpe.
	 * @return An instance of {@link JoinStep} builder.
	 */
	JoinStep innerJoin(SqlTable table);

	/**
	 * Proceeds to the <code>WHERE</code> clause step builder.
	 *
	 * @param filter The initial {@link SqlTypeFilter} instance to add to this step.
	 * @return An instance of {@link WhereStep} builder.
	 */
	WhereStep where(SqlTypeFilter filter);
}
