package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

/**
 * Represents the aggregate column.
 */
public class SqlAggregate implements Projection {

  private final SqlColumn column;

  private final String function;

  private String alias;

  SqlAggregate(SqlColumn column, String function) {
    this.column = column;
    this.function = function;
  }

  /**
   * Creates a new instance of this aggregate of the same function with a new alias.
   * @param alias The new alias of type {@link String} for this aggregate.
   * @return A new instance of {@link SqlAggregate} with the new alias.
   */
  @Override
  public SqlAggregate as(String alias) {
    this.alias = alias;
    return this;
  }

  @Override
  public String project() {
    String sql = function + "(";
    if (!isBlank(column.getTable().getAlias())) {
      sql = sql + column.getTable().getAlias() + ".";
    }
    sql += (column.getName() + ")");
    if (!isBlank(alias)) {
      sql = sql + " AS " + alias;
    }
    return sql;
  }
}
