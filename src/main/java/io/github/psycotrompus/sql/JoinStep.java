package io.github.psycotrompus.sql;

/**
 * Builds the <code>JOIN</code> clause.
 *
 * @author ejlayco
 */
public interface JoinStep extends LimitStep {

	FromStep on(SqlTypeFilter filter);
}
