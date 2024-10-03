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

@JvmInline
value class BooleanLiteral(
    val b: Boolean,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.b.toString(), ArrowTypes.BooleanType)

    override fun toString(): String = "${this.b}"
}

@JvmInline
value class IntegerLiteral(
    val n: Int,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.n.toString(), ArrowTypes.Int32Type)

    override fun toString(): String = "${this.n}"
}

@JvmInline
value class LongLiteral(
    val n: Long,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.n.toString(), ArrowTypes.Int64Type)

    override fun toString(): String = "${this.n}"
}

@JvmInline
value class FloatLiteral(
    val n: Float,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.n.toString(), ArrowTypes.FloatType)

    override fun toString(): String = "${this.n}"
}

@JvmInline
value class DoubleLiteral(
    val n: Double,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.n.toString(), ArrowTypes.DoubleType)

    override fun toString(): String = "${this.n}"
}

@JvmInline
value class StringLiteral(
    val str: String,
) : LogicalExpression {
    override fun toField(context: LogicalOperator): Field = Field(this.str, ArrowTypes.StringType)

    override fun toString(): String = "'${this.str}'"
}

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

class NotEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("neq", "!=", lhs, rhs)

class GreaterThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("gt", ">", lhs, rhs)

class GreaterThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("gte", ">=", lhs, rhs)

class LessThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("lt", "<", lhs, rhs)

class LessThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("lte", "<=", lhs, rhs)

class And(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("and", "AND", lhs, rhs)

class Or(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BinaryBooleanExpression("or", "OR", lhs, rhs)

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

class Subtract(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("sub", "-", lhs, rhs)

class Multiply(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("mul", "*", lhs, rhs)

class Divide(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("div", "/", lhs, rhs)

class Modulus(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : ArithmeticExpression("mod", "%", lhs, rhs)

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
