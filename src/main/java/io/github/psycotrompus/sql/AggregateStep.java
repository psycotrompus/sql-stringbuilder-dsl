package io.github.psycotrompus.sql;

public interface AggregateStep {

  SqlAggregateBuilder groupBy(SqlColumn column);
}
