package io.github.psycotrompus.sql;

public final class Aggregators {

  public static Projection count(Projection projection) {
    return () -> "COUNT(" + projection.project() + ")";
  }

  public static Projection count(SqlColumn column) {
    return new SqlAggregate(column, "COUNT");
  }

  public static Projection sum(SqlColumn column) {
    return new SqlAggregate(column, "SUM");
  }

  public static Projection min(SqlColumn column) {
    return new SqlAggregate(column, "MIN");
  }

  public static Projection max(SqlColumn column) {
    return new SqlAggregate(column, "MAX");
  }

  public static Projection avg(SqlColumn column) {
    return new SqlAggregate(column, "AVG");
  }

  private Aggregators() {
  }
}
