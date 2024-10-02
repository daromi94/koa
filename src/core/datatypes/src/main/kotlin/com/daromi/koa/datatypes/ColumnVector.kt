package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

interface ColumnVector {
    val type: ArrowType

    val size: Int

    fun get(index: Int): Any
}
