package io.github.psycotrompus.sql;

public interface SortingStep extends LimitStep {
	
	FinalStep orderBy(SqlOrder... orders);
}
