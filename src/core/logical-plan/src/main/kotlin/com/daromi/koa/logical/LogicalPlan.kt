package com.daromi.koa.logical

import org.apache.arrow.vector.types.pojo.Schema

interface LogicalPlan {
    val schema: Schema

    val children: List<LogicalPlan>
}

fun format(
    plan: LogicalPlan,
    indent: Int = 0,
): String {
    val b = StringBuilder()
    (0 until indent).forEach { _ -> b.append("\t") }
    b.append(plan).append("\n")
    plan.children.forEach { b.append(format(it, indent + 1)) }
    return b.toString()
}
