package com.external.test;

import io.github.psycotrompus.sql.SqlBuilder;
import io.github.psycotrompus.sql.SqlTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExternalApiTest {

	@Test
	void testSimpleSql() {
		var table = SqlTable.of("table").build();
		var sql = SqlBuilder.select(table.column("column1"))
				.from(table)
				.where(table.column("id").eq("id"))
				.orderBy(table.column("index").asc())
				.build();
		assertEquals("SELECT column1 FROM table WHERE 1=1 AND id = :id ORDER BY index ASC;", sql);
	}

	@Test
	void testWithJoins() {
		var table1 = SqlTable.of("table1").as("t1").build();
		var table2 = SqlTable.of("table2").as("t2").build();
		var sql = SqlBuilder
				.select(table1.column("column1"), table2.column("another_column"))
				.from(table1)
				.leftJoin(table2).on(table1.column("id").eq(table2.column("table1_id")))
				.where(table1.column("id").eq("id"))
				.orderBy(table1.column("index").asc())
				.build();
		assertEquals("SELECT t1.column1, t2.another_column FROM table1 AS t1 LEFT JOIN table2 AS t2 ON t1.id = t2.table1_id WHERE 1=1 AND t1.id = :id ORDER BY t1.index ASC;", sql);
	}

	@Test
	void testWithLimitsAtFrom() {
		var table = SqlTable.of("table").build();
		var sql = SqlBuilder
				.select(table.asterisk())
				.from(table)
				.limit(10)
				.build();
		assertEquals("SELECT * FROM table LIMIT 10;", sql);
	}

	@Test
	void testWithLimitsAtJoin() {
		var table1 = SqlTable.of("table1").as("t1").build();
		var table2 = SqlTable.of("table2").as("t2").build();
		var sql = SqlBuilder
				.select(table1.asterisk())
				.from(table1)
				.leftJoin(table2).on(table1.column("id").eq(table2.column("table1_id")))
				.limit(10, 10)
				.build();
		assertEquals("SELECT t1.* FROM table1 AS t1 LEFT JOIN table2 AS t2 ON t1.id = t2.table1_id LIMIT 10 OFFSET 10;", sql);
	}
}
