package io.github.psycotrompus.sql;

public interface SortingStep {
	
	FinalStep orderBy(SqlOrder... orders);
}
