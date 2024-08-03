package io.github.psycotrompus.sql;

/**
 * Interface for generating partial SQL queries. Mainly used for the majority of classes in this package.
 *
 * @author ejlay
 */
public interface PartialSql {

	/**
	 * The main method to generate part of a SQL statement.
	 * @return a {@link java.lang.String} object
	 */
	String toSql();
}
