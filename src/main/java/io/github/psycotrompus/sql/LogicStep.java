package io.github.psycotrompus.sql;

public interface LogicStep {

	FilterStep and(SqlTypeFilter filter);

	FilterStep or(SqlTypeFilter filter);
}
