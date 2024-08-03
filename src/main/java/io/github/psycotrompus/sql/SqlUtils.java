package io.github.psycotrompus.sql;

/**
 * Utility class for SQL related operations.
 */
final class SqlUtils {

	/**
	 * <p>isBlank.</p>
	 *
	 * @param str a {@link java.lang.String} object
	 * @return a boolean
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	private SqlUtils() {}
}
