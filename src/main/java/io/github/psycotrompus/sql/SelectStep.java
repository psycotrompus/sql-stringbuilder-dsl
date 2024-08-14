package io.github.psycotrompus.sql;

/**
 * Builds the <code>SELECT</code> clause.
 * @author ejlayco
 */
public interface SelectStep {

  /**
   * Proceeds to the next step to build the <code>FROM</code> clause.
   * @param table Theb {@link SqlTable} instance to select from.
   * @return The {@link FromStep} builder.
   */
  FromStep from(SqlTable table);
}
