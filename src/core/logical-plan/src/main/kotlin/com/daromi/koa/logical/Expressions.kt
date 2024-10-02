package com.daromi.koa.logical

import com.daromi.koa.datatypes.ArrowTypes
import com.daromi.koa.datatypes.Field

class Column(
    private val name: String,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field {
        val field = plan.schema.fields.find { it.name == this.name }
        if (field == null) {
            throw NoSuchElementException("no column named '${this.name}'")
        }
        return field
    }

    override fun toString(): String = "#${this.name}"
}

class LiteralInteger(
    private val n: Int,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.n.toString(), ArrowTypes.Int32Type)

    override fun toString(): String = "${this.n}"
}

class LiteralLong(
    private val n: Long,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.n.toString(), ArrowTypes.Int64Type)

    override fun toString(): String = "${this.n}"
}

class LiteralFloat(
    private val n: Float,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.n.toString(), ArrowTypes.FloatType)

    override fun toString(): String = "${this.n}"
}

class LiteralDouble(
    private val n: Double,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.n.toString(), ArrowTypes.DoubleType)

    override fun toString(): String = "${this.n}"
}

class LiteralString(
    private val str: String,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.str, ArrowTypes.StringType)

    override fun toString(): String = "'${this.str}'"
}

abstract class BooleanBinaryExpression(
    private val name: String,
    private val operator: String,
    private val lhs: LogicalExpression,
    private val rhs: LogicalExpression,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.name, ArrowTypes.BooleanType)

    override fun toString(): String = "${this.lhs} ${this.operator} ${this.rhs}"
}

class Equals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("eq", "=", lhs, rhs)

class NotEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("neq", "!=", lhs, rhs)

class GreaterThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("gt", ">", lhs, rhs)

class GreaterThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("gte", ">=", lhs, rhs)

class LessThan(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("lt", "<", lhs, rhs)

class LessThanOrEquals(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("lte", "<=", lhs, rhs)

class And(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("and", "AND", lhs, rhs)

class Or(
    lhs: LogicalExpression,
    rhs: LogicalExpression,
) : BooleanBinaryExpression("or", "OR", lhs, rhs)

abstract class ArithmeticExpression(
    private val name: String,
    private val operator: String,
    private val lhs: LogicalExpression,
    private val rhs: LogicalExpression,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.name, this.lhs.toField(plan).type)

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
    private val name: String,
    private val input: LogicalExpression,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field(this.name, this.input.toField(plan).type)

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

class Count(
    private val input: LogicalExpression,
) : LogicalExpression {
    override fun toField(plan: LogicalPlan): Field = Field("COUNT", ArrowTypes.Int32Type)

    override fun toString(): String = "COUNT(${this.input})"
}
