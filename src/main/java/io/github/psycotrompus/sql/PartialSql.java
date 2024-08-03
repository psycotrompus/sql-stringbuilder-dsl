package io.github.psycotrompus.sql;

/**
 * Interface for generating partial SQL queries. Mainly used for the majority of classes in this package.
 */
public interface PartialSql {
	String toSql();
}
