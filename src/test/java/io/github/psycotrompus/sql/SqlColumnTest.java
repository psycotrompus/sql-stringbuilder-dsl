package io.github.psycotrompus.sql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlColumnTest {

  @Test
  void shouldProduceBasicColumn() {
    // given
    var table = SqlTable.of("table").build();
    var column = table.column("id");

    // when
    final String sql = column.toSql();

    // then
    assertEquals("id", sql);
  }

  @Test
  void shouldProduceColumnWithTableAlias() {
    // given
    var table = SqlTable.of("table").as("t").build();
    var column = table.column("id");

    // when
    final String sql = column.toSql();

    // then
    assertEquals("t.id", sql);
  }

  @Test
  void shouldProduceColumnWithTableAndColumnAliases() {
    // given
    var table = SqlTable.of("table").as("t").build();
    var column = table.column("id").as("pk");

    // when
    final String sql = column.toSql();

    // then
    assertEquals("t.id AS pk", sql);
  }

  @Test
  void shouldProduceEqualsFilter() {
    // given
    var table = SqlTable.of("table").build();
    var column = table.column("id");
    var filter = column.eq("primary");

    // when
    final String sql = filter.toSql();

    // then
    assertEquals("id = :primary", sql);
  }

  @Test
  void shouldProduceInFilterWithAlias() {
    // given
    var table = SqlTable.of("table").as("t").build();
    var column = table.column("id").as("pk");
    var filter = column.in("primary");

    // when
    final String sql = filter.toSql();

    // then
    assertEquals("pk IN :primary", sql);
  }

  @Test
  void shouldProduceFilterWithAnotherColumn() {
    var table1 = SqlTable.of("table").as("t1").build();
    var table2 = SqlTable.of("table2").as("t2").build();
    var idCol = table1.column("id");
    var joinIdCol = table2.column("t1_id");
    var filter = idCol.eq(joinIdCol);

    final var sql = filter.toSql();

    assertEquals("t1.id = t2.t1_id", sql);
  }
}
