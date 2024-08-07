package io.github.psycotrompus.sql;

public interface LimitStep {

	FinalStep limit(int limit);

	FinalStep limit(int limit, Integer offset);
}
