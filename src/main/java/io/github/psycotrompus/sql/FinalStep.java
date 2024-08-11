package io.github.psycotrompus.sql;

/**
 * The final step of building this SQL statement. There are many instances where this can start from:
 *
 * <ul>
 *   <li>{@link FromStep}</li>
 *   <li>{@link WhereStep}</li>
 * </ul>
 */
public interface FinalStep {

	/**
	 * Builds the full SQL statement with a semicolon (<code>;</code>) terminator at the end.
	 *
	 * @return The final SQL {@link String} statement built.
	 */
	String build();
}
