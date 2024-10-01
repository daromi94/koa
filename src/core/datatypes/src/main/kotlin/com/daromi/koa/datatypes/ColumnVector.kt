package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

interface ColumnVector {
    val size: Int

    val type: ArrowType

    fun get(index: Int): Any
}
