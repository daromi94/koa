package com.daromi.koa.physical

import com.daromi.koa.datatypes.ColumnVector
import com.daromi.koa.datatypes.RecordBatch

interface PhysicalExpression {
    fun evaluate(input: RecordBatch): ColumnVector
}
