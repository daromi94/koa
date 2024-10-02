package com.daromi.koa.datatypes

class Batch(
    private val _schema: Schema,
    private val _size: Int,
    private val _columns: List<ColumnVector>,
) {
    val size: Int get() = this._size

    val dimensions: Int get() = this._columns.size

    fun get(index: Int): ColumnVector = this._columns[index]
}
