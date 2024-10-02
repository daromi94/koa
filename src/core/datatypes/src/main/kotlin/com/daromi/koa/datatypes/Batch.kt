package com.daromi.koa.datatypes

class Batch(
    private val schema: Schema,
    private val columns: List<ColumnVector>,
) {
    fun get(index: Int): ColumnVector = this.columns[index]
}
