package io.github.psycotrompus.sql;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Represents a combination of filters in the <code>JOIN</code> and <code>WHERE</code> clause.
 * @author ejlayco
 */
public class SqlGroupedFilter extends SqlTypeFilter {

  private final String operator;

  private final List<SqlTypeFilter> filters;

  SqlGroupedFilter(String operator, List<SqlTypeFilter> filters) {
    this.operator = operator;
    this.filters = filters;
  }

  /**
   * <p>Getter for the field <code>filters</code>.</p>
   * @return a {@link java.util.List} object
   */
  List<SqlTypeFilter> getFilters() {
    return filters;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  String toSql() {
    return "(" + filters.stream().map(SqlTypeFilter::toSql)
        .collect(joining(" " + operator + " ")) + ")";
  }
}
