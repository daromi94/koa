package com.daromi.koa.physical

import com.daromi.koa.datatypes.ArrowTypes
import com.daromi.koa.datatypes.ColumnVector
import com.daromi.koa.datatypes.RecordBatch
import org.apache.arrow.vector.types.pojo.ArrowType

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
    override fun evaluate(input: RecordBatch): ColumnVector =
        object : ColumnVector {
            override val type: ArrowType = ArrowTypes.BooleanType

            override fun get(index: Int): Any {
                if (index !in 0..<input.size) {
                    throw IndexOutOfBoundsException("record index $index out of bounds")
                }
                return value
            }
        }

    override fun toString(): String = this.value.toString()
}

fun lit(value: Boolean): BooleanLiteral = BooleanLiteral(value)

@JvmInline
value class Int8Literal(
    val value: Byte,
) : PhysicalExpression {
    override fun evaluate(input: RecordBatch): ColumnVector =
        object : ColumnVector {
            override val type: ArrowType = ArrowTypes.Int8Type

            override fun get(index: Int): Any {
                if (index !in 0..<input.size) {
                    throw IndexOutOfBoundsException("record index $index out of bounds")
                }
                return value
            }
        }

    override fun toString(): String = this.value.toString()
}

fun lit(value: Byte): Int8Literal = Int8Literal(value)
