package io.github.psycotrompus.sql;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Represents the columns as part of the <code>SELECT</code> clause.
 * @author ejlayco
 */
public class SqlProjection extends PartialSql {

  private final List<Projection> projections;

  /**
   * <p>Constructor for SqlProjection.</p>
   * @param projections a {@link java.util.List} object
   */
  SqlProjection(List<Projection> projections) {
    this.projections = projections;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  String toSql() {
    return projections.stream().map(Projection::project).collect(joining(", "));
  }
}
