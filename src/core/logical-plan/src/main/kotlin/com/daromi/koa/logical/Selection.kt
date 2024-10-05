package com.daromi.koa.logical

import com.daromi.koa.datatypes.Schema

data class Selection(
    val input: LogicalOperator,
    val expression: LogicalExpression,
) : LogicalOperator {
    override val schema: Schema = this.input.schema

    override val children: List<LogicalOperator> = listOf(this.input)

    override fun toString(): String = "Filter: ${this.expression}"
}
