package io.github.psycotrompus.sql;

/**
 * Builds the <code>JOIN</code> clause.
 *
 * @author ejlayco
 */
public interface JoinStep {

	FromStep on(SqlTypeFilter filter);
}
