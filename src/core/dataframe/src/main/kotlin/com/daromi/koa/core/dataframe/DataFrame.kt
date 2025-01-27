package com.daromi.koa.core.dataframe

import com.daromi.koa.core.datasource.DataSource
import com.daromi.koa.core.logical.Aggregate
import com.daromi.koa.core.logical.AggregateExpression
import com.daromi.koa.core.logical.LogicalExpression
import com.daromi.koa.core.logical.LogicalOperator
import com.daromi.koa.core.logical.LogicalPlan
import com.daromi.koa.core.logical.Project
import com.daromi.koa.core.logical.Scan
import com.daromi.koa.core.logical.Select

class DataFrame private constructor(
    private val root: LogicalOperator,
) {
    companion object {
        fun dataSource(
            dataSource: DataSource,
            projection: List<String> = emptyList(),
        ): DataFrame {
            val operator = Scan(dataSource, projection)

            return DataFrame(operator)
        }
    }

    fun select(expression: LogicalExpression): DataFrame {
        val operator = Select(this.root, expression)

        return DataFrame(operator)
    }

    fun project(vararg expressions: LogicalExpression): DataFrame {
        val operator = Project(this.root, expressions.toList())

        return DataFrame(operator)
    }

    fun aggregate(
        aggregateExpressions: List<AggregateExpression>,
        groupByExpressions: List<LogicalExpression>,
    ): DataFrame {
        val operator = Aggregate(this.root, aggregateExpressions, groupByExpressions)

        return DataFrame(operator)
    }

    fun logicalPlan(): LogicalPlan = LogicalPlan(this.root)
}
