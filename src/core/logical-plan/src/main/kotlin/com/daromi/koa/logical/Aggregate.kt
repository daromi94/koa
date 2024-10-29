package com.daromi.koa.logical

import com.daromi.koa.datatypes.Schema

data class Aggregate(
    val input: LogicalOperator,
    val aggregateExpressions: List<AggregateExpression>,
    val groupByExpressions: List<LogicalExpression>,
) : LogicalOperator {
  override val schema: Schema = deriveSchema()

  override val children: List<LogicalOperator> = listOf(this.input)

  private fun deriveSchema(): Schema {
    val aggregateFields = this.aggregateExpressions.map { it.toField(this.input) }

    val groupByFields = this.groupByExpressions.map { it.toField(this.input) }

    return Schema(groupByFields + aggregateFields)
  }

  override fun toString(): String {
    return "Aggregate: aggregate=${this.aggregateExpressions}, groupBy=${this.groupByExpressions}"
  }
}
