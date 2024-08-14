package io.github.psycotrompus.sql;

/**
 * Generates all the components in a FROM SQL clause.
 * @author ejlayco
 */
public class SqlFromBuilder implements FromStep, FinalStep {

  private final SqlBuilderContext context;

  SqlFromBuilder(SqlBuilderContext context) {
    this.context = context;
  }

  /**
   * <p>leftJoin.</p>
   * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
   * @return a {@link io.github.psycotrompus.sql.SqlJoinBuilder} object
   */
  public JoinStep leftJoin(SqlTable table) {
    SqlJoinBuilder join = new SqlJoinBuilder(this, "LEFT JOIN", table);
    context.addJoin(join);
    return join;
  }

  /**
   * <p>innerJoin.</p>
   * @param table a {@link io.github.psycotrompus.sql.SqlTable} object
   * @return a {@link io.github.psycotrompus.sql.SqlJoinBuilder} object
   */
  public JoinStep innerJoin(SqlTable table) {
    SqlJoinBuilder join = new SqlJoinBuilder(this, "INNER JOIN", table);
    context.addJoin(join);
    return join;
  }

  /**
   * <p>where.</p>
   * @param filter a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
   * @return a {@link io.github.psycotrompus.sql.SqlBuilder} object
   */
  public WhereStep where(SqlTypeFilter filter) {
    context.addFilter(filter);
    return new SqlWhereBuilder(context);
  }

  @Override
  public SqlAggregateBuilder groupBy(SqlColumn column) {
    return new SqlAggregateBuilder(context, column);
  }

  @Override
  public FinalStep orderBy(SqlOrder... orders) {
    context.addOrders(orders);
    return context;
  }

  @Override
  public FinalStep limit(int limit) {
    return limit(limit, null);
  }

  @Override
  public FinalStep limit(int limit, Integer offset) {
    context.addLimit(new SqlLimitBuilder(limit, offset));
    return context;
  }

  public String build() {
    return context.build();
  }
}
