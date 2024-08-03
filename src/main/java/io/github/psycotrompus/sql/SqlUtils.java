package io.github.psycotrompus.sql;

/**
 * Utility class for SQL related operations.
 */
final class SqlUtils {

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	private SqlUtils() {}
}
