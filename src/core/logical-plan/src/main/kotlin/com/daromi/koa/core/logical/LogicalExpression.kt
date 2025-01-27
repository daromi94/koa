package com.daromi.koa.core.logical

import com.daromi.koa.core.datatypes.Field

interface LogicalExpression {
    fun toField(context: LogicalOperator): Field
}
