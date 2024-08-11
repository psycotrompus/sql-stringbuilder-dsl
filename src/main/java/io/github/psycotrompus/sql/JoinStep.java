package io.github.psycotrompus.sql;

/**
 * Builds the <code>JOIN</code> clause.
 *
 * @author ejlayco
 */
public interface JoinStep extends LimitStep {

	/**
	 * Which condition should this <code>JOIN</code> clause combine to.
	 *
	 * @param filter The {@link SqlTypeFilter} instance to combine this clause.
	 * @return The {@link FromStep} builder.
	 */
	FromStep on(SqlTypeFilter filter);
}
