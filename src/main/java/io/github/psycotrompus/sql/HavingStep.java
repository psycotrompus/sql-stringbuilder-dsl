package io.github.psycotrompus.sql;

public interface HavingStep extends SortingStep, LimitStep, FinalStep {

  SqlAggregateBuilder having(SqlTypeFilter filter);
}
