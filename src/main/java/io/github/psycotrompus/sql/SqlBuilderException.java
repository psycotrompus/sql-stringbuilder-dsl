package io.github.psycotrompus.sql;

/**
 * Exception thrown when an error occurs during SQL validation and building.
 */
public class SqlBuilderException extends RuntimeException {

	public SqlBuilderException(String message) {
		super(message);
	}
}
