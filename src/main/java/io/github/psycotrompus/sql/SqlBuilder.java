package io.github.psycotrompus.sql;

import static java.util.Arrays.asList;

/**
 * Starting class for building SQL queries. The pattern implementation is based on the Step Builder pattern.
 * @author ejlayco
 */
public class SqlBuilder implements SelectStep {

  /**
   * Creates a new {@link SqlBuilder} instance for building a SELECT query.
   * @param projections A variable arguments of type {@link Projection}.
   * @return a new {@link SqlBuilder} instance
   */
  public static SelectStep select(Projection... projections) {
    var projection = new SqlProjection(asList(projections));
    var context = new SqlBuilderContext(projection);
    return new SqlBuilder(context);
  }

  private final SqlBuilderContext context;

  private SqlBuilder(SqlBuilderContext context) {
    this.context = context;
  }

  /**
   * {@inheritDoc}
   */
  public FromStep from(SqlTable table) {
    context.setRootTable(table);
    return new SqlFromBuilder(context);
  }

}
