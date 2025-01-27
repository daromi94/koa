package com.daromi.koa.core.physical

import com.daromi.koa.core.datatypes.ColumnVector
import com.daromi.koa.core.datatypes.RecordBatch

@JvmInline
value class Column(
    val index: Int,
) : PhysicalExpression {
    override fun evaluate(input: RecordBatch): ColumnVector = input[this.index]

    override fun toString(): String = "#${this.index}"
}

fun col(index: Int): Column = Column(index)

@JvmInline
value class BooleanLiteral(
    val value: Boolean,
) : PhysicalExpression {
    override fun evaluate(input: RecordBatch): ColumnVector = TODO("Not yet implemented")

    override fun toString(): String = this.value.toString()
}

fun lit(value: Boolean): BooleanLiteral = BooleanLiteral(value)

@JvmInline
value class Int8Literal(
    val value: Byte,
) : PhysicalExpression {
    override fun evaluate(input: RecordBatch): ColumnVector = TODO("Not yet implemented")

    override fun toString(): String = this.value.toString()
}

fun lit(value: Byte): Int8Literal = Int8Literal(value)
