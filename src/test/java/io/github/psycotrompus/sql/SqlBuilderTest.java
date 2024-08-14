package io.github.psycotrompus.sql;

import org.junit.jupiter.api.Test;

import static io.github.psycotrompus.sql.Aggregators.count;
import static org.junit.jupiter.api.Assertions.*;

class SqlBuilderTest {

	@Test
	void testSimpleStatement() {
		var table = SqlTable.of("table").build();
		var sql = SqlBuilder
				.select(table.asterisk())
				.from(table)
				.where(table.column("id").eq("id"))
				.build();
		assertEquals("SELECT * FROM table WHERE 1=1 AND id = :id;", sql);
	}

	@Test
	void testSimpleWithTableAliasAndOrderBy() {
		var table = SqlTable.of("table").as("t").build();
		var sql = SqlBuilder
				.select(table.asterisk())
				.from(table)
				.where(table.column("id").eq("id"))
				.orderBy(table.column("name").asc())
				.build();
		assertEquals("SELECT t.* FROM table AS t WHERE 1=1 AND t.id = :id ORDER BY t.name ASC;", sql);
	}

	@Test
	void testSelectWithJoinsWhereGroupByAndOrderBy() {
		SqlTable tenant = SqlTable.of("tenant").on("schema1").as("t").build();
		SqlTable profile = SqlTable.of("profile").on("schema2").as("p").build();
		SqlTable user = SqlTable.of("user").on("schema3").as("u").build();

		var sql = SqlBuilder
				.select(user.column("tenant_id"), count(tenant.asterisk()))
				.from(tenant)
				.leftJoin(profile).on(tenant.column("id").eq(profile.column("tenant_id")))
				.innerJoin(user).on(profile.column("id").eq(user.column("profile_id")))
				.where(
						tenant.column("id").in("id")
								.or(tenant.column("name").eq("name"))
				)
				.and(tenant.column("active").isFalse().or(tenant.column("active").isNull()))
				.and(profile.column("name").like("prefix"))
				.groupBy(user.column("tenant_id")).having(user.column("active").isTrue())
				.orderBy(profile.column("name").asc(), tenant.column("date_registered").desc())
				.build();

		assertEquals("SELECT u.tenant_id, COUNT(t.*) FROM schema1.tenant AS t LEFT JOIN schema2.profile AS p " +
				"ON t.id = p.tenant_id INNER JOIN schema3.user AS u ON p.id = u.profile_id WHERE 1=1 AND (t.id IN " +
				":id OR t.name = :name) AND (t.active IS false OR t.active IS null) AND p.name LIKE :prefix GROUP " +
				"BY u.tenant_id HAVING u.active IS true ORDER BY p.name ASC, t.date_registered DESC;", sql);
	}

}