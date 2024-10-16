package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

data class RecordBatch(
    val schema: Schema,
    val size: Int,
    val columns: List<ColumnVector>,
) {
    operator fun get(index: Int): ColumnVector {
        if (index !in 0..<this.columns.size) {
            throw IndexOutOfBoundsException("column index $index out of bounds")
        }

        return this.columns[index]
    }
}

interface ColumnVector {
    val type: ArrowType

    operator fun get(index: Int): Any
}
