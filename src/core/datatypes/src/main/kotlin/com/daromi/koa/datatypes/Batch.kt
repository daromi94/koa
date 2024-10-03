package com.daromi.koa.datatypes

data class Batch(
    val schema: Schema,
    val columns: List<ColumnVector>,
) {
    fun get(index: Int): ColumnVector {
        assert(index in 0..<this.columns.size)

        return this.columns[index]
    }
}
