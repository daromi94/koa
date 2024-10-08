package com.daromi.koa.logical

import com.daromi.koa.datatypes.Schema

data class Aggregate(
    val input: LogicalOperator,
    val groupByExpressions: List<LogicalExpression>,
    val aggregateExpressions: List<AggregateExpression>,
) : LogicalOperator {
    override val schema: Schema = deriveSchema()

    override val children: List<LogicalOperator> = listOf(this.input)

    private fun deriveSchema(): Schema {
        val groupByFields = this.groupByExpressions.map { it.toField(this.input) }

        val aggregateFields = this.aggregateExpressions.map { it.toField(this.input) }

        return Schema(groupByFields + aggregateFields)
    }

    override fun toString(): String = "Aggregate: groupBy=${this.groupByExpressions}, aggregate=${this.aggregateExpressions}"
}
