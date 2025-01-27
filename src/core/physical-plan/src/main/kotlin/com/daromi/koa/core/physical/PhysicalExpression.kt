package com.daromi.koa.core.physical

import com.daromi.koa.core.datatypes.ColumnVector
import com.daromi.koa.core.datatypes.RecordBatch

interface PhysicalExpression {
    fun evaluate(input: RecordBatch): ColumnVector
}
