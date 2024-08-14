package io.github.psycotrompus.sql;

/**
 * Interface for generating partial SQL queries.
 * Used internally for the majority of classes in this package.
 * @author ejlayco
 */
abstract class PartialSql {

  /**
   * The main method to generate part of a SQL statement.
   * @return a {@link java.lang.String} object
   */
  abstract String toSql();
}
