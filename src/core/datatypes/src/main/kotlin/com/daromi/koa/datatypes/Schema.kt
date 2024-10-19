package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.pojo.ArrowType

class Schema private constructor(private val fields: Map<FieldName, Field>) {

  fun project(fieldNames: List<FieldName>): Schema {
    val fields = mutableMapOf<FieldName, Field>()

    for (fieldName in fieldNames) {
      val field = this.fields[fieldName]
      if (field == null) {
        throw IllegalArgumentException("unknown field '$fieldName'")
      }
      fields[fieldName] = field
    }

    return Schema(fields)
  }
}

typealias FieldName = String

typealias FieldType = ArrowType

data class Field(val name: FieldName, val type: FieldType)
