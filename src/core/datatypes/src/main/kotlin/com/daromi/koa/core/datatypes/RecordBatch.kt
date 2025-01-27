package com.daromi.koa.core.datatypes

class RecordBatch private constructor(
    private val schema: Schema,
    private val size: UInt,
    private val columns: List<ColumnVector>,
) {
    operator fun get(index: Int): ColumnVector {
        if (index !in 0..<this.columns.size) {
            throw IndexOutOfBoundsException("column index $index out of bounds")
        }

        return this.columns[index]
    }
}

interface ColumnVector {
    val type: FieldType

    operator fun get(index: Int): Any
}
