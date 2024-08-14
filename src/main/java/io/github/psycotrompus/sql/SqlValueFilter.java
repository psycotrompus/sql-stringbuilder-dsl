package io.github.psycotrompus.sql;

import static io.github.psycotrompus.sql.SqlUtils.isBlank;

/**
 * References a parameterized value filter in a SQL statement.
 * The format is:
 * <pre>
 *   table_alias.column_name [comparator] parameter
 * </pre>
 *
 * Sample output:
 * <pre>
 *   table.column_name = :parameter
 * </pre>
 * OR
 * <pre>
 *   table.column_name IS true
 * </pre>
 * OR
 * <pre>
 *   column_alias IN :parameter
 * </pre>
 * @author ejlayco
 */
public class SqlValueFilter extends SqlTypeFilter {

  private final SqlColumn column;

  private final String comparator;

  private final String parameter;

  SqlValueFilter(SqlColumn column, String comparator, String parameter) {
    this.column = column;
    this.comparator = comparator;
    this.parameter = parameter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  String toSql() {
    StringBuilder sb = new StringBuilder();
    if (!isBlank(column.getAlias())) {
      sb.append(column.getAlias());
    }
    else if (!isBlank(column.getTable().getAlias())) {
      sb.append(column.getTable().getAlias()).append(".").append(column.getName());
    }
    else {
      sb.append(column.getName());
    }
    sb.append(" ").append(comparator).append(" ").append(parameter);
    return sb.toString();
  }
}
