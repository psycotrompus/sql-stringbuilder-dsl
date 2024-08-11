package io.github.psycotrompus.sql;

/**
 * The step to build the <code>LIMIT</code> clause. This is used
 * to create a paginated result.
 */
public interface LimitStep {

	/**
	 * Creates a <code>LIMIT</code> clause with the number of items to show.
	 *
	 * @param limit The number of items to limit the result.
	 * @return The {@link FinalStep} builder for this statement.
	 */
	FinalStep limit(int limit);

	/**
	 * Creates a <code>LIMIT</code> clause with an <code>OFFSET</code> value.
	 *
	 * @param limit The number of items to limit the result.
	 * @param offset Which row should offset the result.
	 * @return The {@link FinalStep} builder for this statement.
	 */
	FinalStep limit(int limit, Integer offset);
}
