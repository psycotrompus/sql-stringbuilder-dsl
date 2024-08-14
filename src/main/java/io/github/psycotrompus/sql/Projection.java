package io.github.psycotrompus.sql;

public interface Projection {

  String project();

  default Projection as(String alias) {
    return () -> alias;
  }
}
