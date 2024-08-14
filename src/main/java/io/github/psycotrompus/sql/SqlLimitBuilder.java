package io.github.psycotrompus.sql;

class SqlLimitBuilder extends PartialSql {

  private final Integer limit;

  private Integer offset;

  SqlLimitBuilder(Integer limit) {
    this(limit, null);
  }

  SqlLimitBuilder(Integer limit, Integer offset) {
    this.limit = limit;
    this.offset = offset;
  }

  @Override
  String toSql() {
    if (offset != null && offset > 0) {
      return String.format(" LIMIT %d OFFSET %d", limit, offset);
    }
    return String.format(" LIMIT %d", limit);
  }
}
