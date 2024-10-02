package com.daromi.koa.logical

import com.daromi.koa.datatypes.Field

interface LogicalExpression {
    fun toField(plan: LogicalPlan): Field
}
