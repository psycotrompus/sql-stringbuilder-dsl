package io.github.psycotrompus.sql;

public class SqlAggregateBuilder extends PartialSql implements HavingStep {

  private final SqlBuilderContext context;

  private final SqlColumn column;

  private SqlTypeFilter filter;

  SqlAggregateBuilder(SqlBuilderContext context, SqlColumn column) {
    this.context = context;
    this.column = column;
  }

  public SqlAggregateBuilder having(SqlTypeFilter filter) {
    this.filter = filter;
    return this;
  }

  @Override
  public FinalStep orderBy(SqlOrder... orders) {
    context.setAggregate(this);
    context.addOrders(orders);
    return context;
  }

  @Override
  public FinalStep limit(int limit) {
    return limit(limit, null);
  }

  @Override
  public FinalStep limit(int limit, Integer offset) {
    context.setAggregate(this);
    context.addLimit(new SqlLimitBuilder(limit, offset));
    return context;
  }

  @Override
  public String build() {
    context.setAggregate(this);
    return context.build();
  }

  @Override
  String toSql() {
    StringBuilder sb = new StringBuilder("GROUP BY ").append(column.toSql());
    if (filter != null) {
      sb.append(" HAVING ").append(filter.toSql());
    }
    return sb.toString();
  }
}
