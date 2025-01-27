package com.daromi.koa.core.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

class Schema private constructor(
    private val fields: Map<FieldName, Field>,
) {
    fun field(name: FieldName): Field? = this.fields[name]

    fun project(names: List<FieldName>): Schema {
        val fields = mutableMapOf<FieldName, Field>()

        for (name in names) {
            val field = this.fields[name] ?: throw IllegalArgumentException("unknown field '$name'")
            fields[name] = field
        }

        return Schema(fields)
    }
}

typealias FieldName = String

typealias FieldType = ArrowType

data class Field(
    val name: FieldName,
    val type: FieldType,
)
