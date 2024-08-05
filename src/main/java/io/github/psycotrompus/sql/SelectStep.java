package io.github.psycotrompus.sql;

/**
 * Builds the <code>SELECT</code> clause.
 *
 * @author ejlayco
 */
public interface SelectStep {

	FromStep from(SqlTable table);
}
