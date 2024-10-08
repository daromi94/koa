package com.daromi.koa.dataframe

import com.daromi.koa.datasource.DataSource
import com.daromi.koa.logical.Aggregate
import com.daromi.koa.logical.AggregateExpression
import com.daromi.koa.logical.Filter
import com.daromi.koa.logical.LogicalExpression
import com.daromi.koa.logical.LogicalOperator
import com.daromi.koa.logical.LogicalPlan
import com.daromi.koa.logical.Project
import com.daromi.koa.logical.Scan

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

    fun project(expressions: List<LogicalExpression>): DataFrame {
        val operator = Project(this.root, expressions)
        return DataFrame(operator)
    }

    fun filter(expression: LogicalExpression): DataFrame {
        val operator = Filter(this.root, expression)
        return DataFrame(operator)
    }

    fun aggregate(
        groupByExpressions: List<LogicalExpression>,
        aggregateExpressions: List<AggregateExpression>,
    ): DataFrame {
        val operator = Aggregate(this.root, groupByExpressions, aggregateExpressions)
        return DataFrame(operator)
    }

    fun logicalPlan(): LogicalPlan = LogicalPlan(this.root)
}
