package io.github.psycotrompus.sql;

/**
 * Exception thrown when an error occurs during SQL validation and building.
 *
 * @author ejlayco
 */
public class SqlBuilderException extends RuntimeException {

	/**
	 * <p>Constructor for SqlBuilderException.</p>
	 *
	 * @param message a {@link java.lang.String} object
	 */
	public SqlBuilderException(String message) {
		super(message);
	}
}
