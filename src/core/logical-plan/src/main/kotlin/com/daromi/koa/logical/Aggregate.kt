package com.daromi.koa.logical

import com.daromi.koa.datatypes.Schema

data class Aggregate(
    val input: LogicalOperator,
    val groupExpressions: List<LogicalExpression>,
    val aggregateExpressions: List<AggregateExpression>,
) : LogicalOperator {
    override val schema: Schema = deriveSchema()

    override val children: List<LogicalOperator> = listOf(this.input)

    private fun deriveSchema(): Schema {
        val groupFields = this.groupExpressions.map { it.toField(this.input) }

        val aggregateFields = this.aggregateExpressions.map { it.toField(this.input) }

        return Schema(groupFields + aggregateFields)
    }

    override fun toString(): String = "Aggregate: group=${this.groupExpressions}, aggregate=${this.aggregateExpressions}"
}
