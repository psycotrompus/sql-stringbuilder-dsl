package io.github.psycotrompus.sql;

/**
 * The step to build the <code>ORDER BY</code> clause.
 */
public interface SortingStep extends LimitStep {

  /**
   * Add the columns to order this result. The {@link SqlOrder} instances are created
   * through the {@link SqlColumn#asc()} or {@link SqlColumn#desc()} methods.
   * @param orders Which columns to order the result.
   * @return The {@link FinalStep} builder
   */
  FinalStep orderBy(SqlOrder... orders);
}
