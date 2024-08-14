package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

/**
 * Represents a JOIN clause when generating SQL query.
 * @author ejlayco
 */
public class SqlJoinBuilder extends PartialSql implements JoinStep {

  private final SqlFromBuilder fromBuilder;

  private final String joinType;

  private final SqlTable table;

  private SqlTypeFilter filter;

  /**
   * <p>Constructor for SqlJoinBuilder.</p>
   * @param fromBuilder a {@link io.github.psycotrompus.sql.SqlFromBuilder} object
   * @param joinType    a {@link java.lang.String} object
   * @param table       a {@link io.github.psycotrompus.sql.SqlTable} object
   */
  public SqlJoinBuilder(SqlFromBuilder fromBuilder, String joinType, SqlTable table) {
    this.fromBuilder = fromBuilder;
    this.joinType = joinType;
    this.table = table;
  }

  /**
   * <p>on.</p>
   * @param filter a {@link io.github.psycotrompus.sql.SqlTypeFilter} object
   * @return a {@link io.github.psycotrompus.sql.SqlFromBuilder} object
   */
  public FromStep on(SqlTypeFilter filter) {
    this.filter = filter;
    return fromBuilder;
  }

  @Override
  public FinalStep limit(int limit) {
    return fromBuilder.limit(limit);
  }

  @Override
  public FinalStep limit(int limit, Integer offset) {
    return fromBuilder.limit(limit, offset);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  String toSql() {
    if (isBlank(table.getAlias())) {
      throw new SqlBuilderException("Table alias is required.");
    }
    return String.format("%s %s ON %s", joinType, table.toSql(), filter.toSql());
  }
}
