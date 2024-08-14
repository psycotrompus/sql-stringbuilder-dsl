package io.github.psycotrompus.sql;

public interface FilterStep {

  LogicStep eq(SqlColumn column);

  LogicStep eq(String parameter);

  LogicStep in(String parameter);

  LogicStep isTrue();

  LogicStep isFalse();

  LogicStep isNull();

  LogicStep like(String parameter);
}
