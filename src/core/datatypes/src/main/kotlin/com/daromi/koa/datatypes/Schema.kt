package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

data class Schema(
    val fields: List<Field>,
) {
    fun select(fieldNames: List<String>): Schema {
        val fields = mutableListOf<Field>()

        for (fieldName in fieldNames) {
            val field = this.fields.find { it.name == fieldName }
            if (field == null) {
                throw IllegalArgumentException("no field named '$fieldName'")
            }
            fields.add(field)
        }

        return Schema(fields)
    }
}

data class Field(
    val name: String,
    val type: ArrowType,
)