package com.daromi.koa.logical

import com.daromi.koa.datatypes.ArrowTypes
import com.daromi.koa.datatypes.Field

@JvmInline
value class Column(
    val name: String,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field {
        val field = context.schema.fields.find { it.name == this.name }
        if (field == null) {
            throw NoSuchElementException("unknown column '${this.name}'")
        }
        return field
    }

    override fun toString(): String = "#${this.name}"
}

fun col(name: String): Column = Column(name)

@JvmInline
value class BooleanLiteral(
    val value: Boolean,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.value.toString(), ArrowTypes.BooleanType)

    override fun toString(): String = this.value.toString()
}

fun lit(value: Boolean): BooleanLiteral = BooleanLiteral(value)

@JvmInline
value class IntegerLiteral(
    val value: Int,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.value.toString(), ArrowTypes.Int32Type)

    override fun toString(): String = this.value.toString()
}

fun lit(value: Int): IntegerLiteral = IntegerLiteral(value)

@JvmInline
value class LongLiteral(
    val value: Long,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.value.toString(), ArrowTypes.Int64Type)

    override fun toString(): String = this.value.toString()
}

fun lit(value: Long): LongLiteral = LongLiteral(value)

@JvmInline
value class FloatLiteral(
    val value: Float,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.value.toString(), ArrowTypes.FloatType)

    override fun toString(): String = this.value.toString()
}

fun lit(value: Float): FloatLiteral = FloatLiteral(value)

@JvmInline
value class DoubleLiteral(
    val value: Double,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.value.toString(), ArrowTypes.DoubleType)

    override fun toString(): String = this.value.toString()
}

fun lit(value: Double): DoubleLiteral = DoubleLiteral(value)

@JvmInline
value class StringLiteral(
    val value: String,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.value, ArrowTypes.StringType)

    override fun toString(): String = "\"${this.value}\""
}

fun lit(value: String): StringLiteral = StringLiteral(value)

abstract class BinaryBooleanExpression(
    val name: String,
    val operator: String,
    val lhs: LogicalExpression,
    val rhs: LogicalExpression,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.name, ArrowTypes.BooleanType)

    override fun toString(): String = "${this.lhs} ${this.operator} ${this.rhs}"
}

class Equals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("eq", "=", lhs, rhs)

infix fun LogicalExpression.eq(rhs: LogicalExpression): Equals = Equals(this, rhs)

class NotEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("neq", "!=", lhs, rhs)

infix fun LogicalExpression.neq(rhs: LogicalExpression): NotEquals = NotEquals(this, rhs)

class GreaterThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("gt", ">", lhs, rhs)

infix fun LogicalExpression.gt(rhs: LogicalExpression): GreaterThan = GreaterThan(this, rhs)

class GreaterThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("gte", ">=", lhs, rhs)

infix fun LogicalExpression.gte(rhs: LogicalExpression): GreaterThanOrEquals = GreaterThanOrEquals(this, rhs)

class LessThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("lt", "<", lhs, rhs)

infix fun LogicalExpression.lt(rhs: LogicalExpression): LessThan = LessThan(this, rhs)

class LessThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("lte", "<=", lhs, rhs)

infix fun LogicalExpression.lte(rhs: LogicalExpression): LessThanOrEquals = LessThanOrEquals(this, rhs)

class And(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("and", "AND", lhs, rhs)

infix fun LogicalExpression.and(rhs: LogicalExpression): And = And(this, rhs)

class Or(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("or", "OR", lhs, rhs)

infix fun LogicalExpression.or(rhs: LogicalExpression): Or = Or(this, rhs)

abstract class ArithmeticExpression(
    val name: String,
    val operator: String,
    val lhs: LogicalExpression,
    val rhs: LogicalExpression,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.name, this.lhs.toField(context).type)

    override fun toString(): String = "${this.lhs} ${this.operator} ${this.rhs}"
}

class Add(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("add", "+", lhs, rhs)

infix operator fun LogicalExpression.plus(rhs: LogicalExpression): Add = Add(this, rhs)

class Subtract(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("sub", "-", lhs, rhs)

infix operator fun LogicalExpression.minus(rhs: LogicalExpression): Subtract = Subtract(this, rhs)

class Multiply(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("mul", "*", lhs, rhs)

infix operator fun LogicalExpression.times(rhs: LogicalExpression): Multiply = Multiply(this, rhs)

class Divide(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("div", "/", lhs, rhs)

infix operator fun LogicalExpression.div(rhs: LogicalExpression): Divide = Divide(this, rhs)

class Modulus(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("mod", "%", lhs, rhs)

infix operator fun LogicalExpression.rem(rhs: LogicalExpression): Modulus = Modulus(this, rhs)

abstract class AggregateExpression(
    val name: String,
    val input: LogicalExpression,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.name, this.input.toField(context).type)

    override fun toString(): String = "${this.name}(${this.input})"
}

class Sum(
    input: LogicalExpression,
) : AggregateExpression("SUM", input)

class Min(
    input: LogicalExpression,
) : AggregateExpression("MIN", input)

class Max(
    input: LogicalExpression,
) : AggregateExpression("MAX", input)

class Avg(
    input: LogicalExpression,
) : AggregateExpression("AVG", input)

data class Count(
    val input: LogicalExpression,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field("COUNT", ArrowTypes.Int32Type)

    override fun toString(): String = "COUNT(${this.input})"
}
