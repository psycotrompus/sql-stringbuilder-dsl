package io.github.psycotrompus.sql;

public final class SqlUtils {

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	private SqlUtils() {}
}
