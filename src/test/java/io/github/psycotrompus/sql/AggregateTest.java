package io.github.psycotrompus.sql;

import org.junit.jupiter.api.Test;

import static io.github.psycotrompus.sql.Aggregators.avg;
import static io.github.psycotrompus.sql.Aggregators.count;
import static io.github.psycotrompus.sql.Aggregators.min;
import static io.github.psycotrompus.sql.Aggregators.sum;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AggregateTest {

  @Test
  void shouldProduceCountAggregate() {
    var table = SqlTable.of("table").build();
    var column = table.column("id");
    var total = count(column);

    final var sql = total.project();

    assertEquals("COUNT(id)", sql);
  }

  @Test
  void shouldProduceSumWithAlias() {
    var table = SqlTable.of("table").build();
    var column = table.column("age");
    var total = sum(column).as("total");

    final var sql = total.project();

    assertEquals("SUM(age) AS total", sql);
  }

  @Test
  void shouldAverageUsingColumnAlias() {
    var table = SqlTable.of("table").build();
    var column = table.column("age").as("edad");
    var total = avg(column).as("total");

    final var sql = total.project();

    assertEquals("AVG(age) AS total", sql);
  }

  @Test
  void shouldMinUsingTableAliases() {
    var table = SqlTable.of("table").as("t").build();
    var column = table.column("age");
    var total = min(column).as("minimum");

    final var sql = total.project();

    assertEquals("MIN(t.age) AS minimum", sql);
  }
}
