package com.daromi.koa.core.logical

import com.daromi.koa.core.datatypes.Schema

data class Project(
    val input: LogicalOperator,
    val expressions: List<LogicalExpression>,
) : LogicalOperator {
    override val schema: Schema = deriveSchema()

    override val children: List<LogicalOperator> = listOf(this.input)

    private fun deriveSchema(): Schema {
        val fields = this.expressions.map { it.toField(this.input) }

        return Schema(fields)
    }

    override fun toString(): String = "Project: ${this.expressions}"
}
