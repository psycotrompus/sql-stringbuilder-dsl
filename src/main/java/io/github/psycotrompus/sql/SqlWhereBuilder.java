package io.github.psycotrompus.sql;

class SqlWhereBuilder implements WhereStep {

	private final SqlBuilderContext context;

	SqlWhereBuilder(SqlBuilderContext context) {
		this.context = context;
	}

	@Override
	public WhereStep and(SqlTypeFilter filter) {
		context.addFilter(filter);
		return this;
	}

	@Override
	public WhereStep or(SqlTypeFilter filter) {
		context.addFilter(filter);
		return this;
	}

	@Override
	public FinalStep orderBy(SqlOrder... orders) {
		for (SqlOrder order : orders) {
			context.addOrder(order);
		}
		return context;
	}

	@Override
	public String build() {
		return context.build();
	}
}
