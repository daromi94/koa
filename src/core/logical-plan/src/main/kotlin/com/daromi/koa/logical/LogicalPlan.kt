package com.daromi.koa.logical

import com.daromi.koa.datatypes.Schema

interface LogicalPlan {
    val root: LogicalOperator

    fun pretty(): String = format(this.root)
}

interface LogicalOperator {
    val schema: Schema

    val children: List<LogicalOperator>
}

private fun format(
    operator: LogicalOperator,
    indent: Int = 0,
): String {
    val sb = StringBuilder()

    repeat(indent) { sb.append("\t") }

    sb.append(operator.toString()).append("\n")

    operator.children.forEach { sb.append(format(it, indent + 1)) }

    return sb.toString()
}
