package com.daromi.koa.logical

import com.daromi.koa.datatypes.Field
import com.daromi.koa.datatypes.Types

@JvmInline
value class Column(private val name: String) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    val field = context.schema.field(this.name)
    if (field == null) {
      throw NoSuchElementException("unknown column '${this.name}'")
    }
    return field
  }

  override fun toString(): String = "#${this.name}"
}

fun col(name: String) = Column(name)

@JvmInline
value class BooleanLiteral(private val value: Boolean) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Boolean)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Boolean) = BooleanLiteral(value)

@JvmInline
value class Int8Literal(private val value: Byte) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Int8)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Byte) = Int8Literal(value)

@JvmInline
value class Int16Literal(private val value: Short) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Int16)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Short) = Int16Literal(value)

@JvmInline
value class Int32Literal(private val value: Int) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Int32)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Int) = Int32Literal(value)

@JvmInline
value class Int64Literal(private val value: Long) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Int64)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Long) = Int64Literal(value)

@JvmInline
value class UInt8Literal(private val value: UByte) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.UInt8)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: UByte) = UInt8Literal(value)

@JvmInline
value class UInt16Literal(private val value: UShort) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.UInt16)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: UShort) = UInt16Literal(value)

@JvmInline
value class UInt32Literal(private val value: UInt) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.UInt32)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: UInt) = UInt32Literal(value)

@JvmInline
value class UInt64Literal(private val value: ULong) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.UInt64)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: ULong) = UInt64Literal(value)

@JvmInline
value class FloatLiteral(private val value: Float) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Float)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Float) = FloatLiteral(value)

@JvmInline
value class DoubleLiteral(private val value: Double) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value.toString(), Types.Double)
  }

  override fun toString(): String = this.value.toString()
}

fun lit(value: Double) = DoubleLiteral(value)

@JvmInline
value class StringLiteral(private val value: String) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.value, Types.String)
  }

  override fun toString(): String = "\"${this.value}\""
}

fun lit(value: String) = StringLiteral(value)

abstract class BinaryBooleanExpression(
    private val name: String,
    private val symbol: String,
    private val lhs: LogicalExpression,
    private val rhs: LogicalExpression,
) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field(this.name, Types.Boolean)
  }

  override fun toString(): String = "${this.lhs} ${this.symbol} ${this.rhs}"
}

class Equals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("eq", "=", lhs, rhs)

infix fun LogicalExpression.eq(rhs: LogicalExpression) = Equals(this, rhs)

class NotEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("neq", "!=", lhs, rhs)

infix fun LogicalExpression.neq(rhs: LogicalExpression) = NotEquals(this, rhs)

class GreaterThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("gt", ">", lhs, rhs)

infix fun LogicalExpression.gt(rhs: LogicalExpression) = GreaterThan(this, rhs)

class GreaterThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("gte", ">=", lhs, rhs)

infix fun LogicalExpression.gte(rhs: LogicalExpression) = GreaterThanOrEquals(this, rhs)

class LessThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("lt", "<", lhs, rhs)

infix fun LogicalExpression.lt(rhs: LogicalExpression) = LessThan(this, rhs)

class LessThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("lte", "<=", lhs, rhs)

infix fun LogicalExpression.lte(rhs: LogicalExpression) = LessThanOrEquals(this, rhs)

class And(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("and", "AND", lhs, rhs)

infix fun LogicalExpression.and(rhs: LogicalExpression) = And(this, rhs)

class Or(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("or", "OR", lhs, rhs)

infix fun LogicalExpression.or(rhs: LogicalExpression) = Or(this, rhs)

abstract class BinaryArithmeticExpression(
    private val name: String,
    private val symbol: String,
    private val lhs: LogicalExpression,
    private val rhs: LogicalExpression,
) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    val leftField = this.lhs.toField(context)

    return Field(this.name, leftField.type)
  }

  override fun toString(): String = "${this.lhs} ${this.symbol} ${this.rhs}"
}

class Add(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryArithmeticExpression("add", "+", lhs, rhs)

infix operator fun LogicalExpression.plus(rhs: LogicalExpression) = Add(this, rhs)

class Subtract(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryArithmeticExpression("sub", "-", lhs, rhs)

infix operator fun LogicalExpression.minus(rhs: LogicalExpression) = Subtract(this, rhs)

class Multiply(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryArithmeticExpression("mul", "*", lhs, rhs)

infix operator fun LogicalExpression.times(rhs: LogicalExpression) = Multiply(this, rhs)

class Divide(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryArithmeticExpression("div", "/", lhs, rhs)

infix operator fun LogicalExpression.div(rhs: LogicalExpression) = Divide(this, rhs)

class Modulus(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryArithmeticExpression("mod", "%", lhs, rhs)

infix operator fun LogicalExpression.rem(rhs: LogicalExpression) = Modulus(this, rhs)

abstract class AggregateExpression(
    private val name: String,
    private val input: LogicalExpression,
) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    val leftField = this.input.toField(context)

    return Field(this.name, leftField.type)
  }

  override fun toString(): String = "${this.name}(${this.input})"
}

class Sum(input: LogicalExpression) : AggregateExpression("SUM", input)

class Min(input: LogicalExpression) : AggregateExpression("MIN", input)

class Max(input: LogicalExpression) : AggregateExpression("MAX", input)

class Avg(input: LogicalExpression) : AggregateExpression("AVG", input)

class Count(private val input: LogicalExpression) : LogicalExpression {

  override fun toField(context: LogicalOperator): Field {
    return Field("COUNT", Types.Int32)
  }

  override fun toString(): String = "COUNT(${this.input})"
}
