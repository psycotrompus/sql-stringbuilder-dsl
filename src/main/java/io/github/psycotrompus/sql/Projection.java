package io.github.psycotrompus.sql;

/**
 * The interface that represents data projections. These are usually used
 * as part of the output of the query.
 */
public interface Projection {

  String project();

  default Projection as(String alias) {
    return () -> alias;
  }
}
