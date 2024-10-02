package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

data class Schema(
    val fields: List<Field>,
)

data class Field(
    val name: String,
    val type: ArrowType,
)
