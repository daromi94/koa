package com.daromi.koa.logical

import com.daromi.koa.datasource.DataSource
import com.daromi.koa.datatypes.Schema

data class Scan(
    val dataSource: DataSource,
    val projection: List<String>,
) : LogicalOperator {
    override val schema: Schema = deriveSchema()

    override val children: List<LogicalOperator> = emptyList()

    private fun deriveSchema(): Schema {
        val schema = this.dataSource.schema

        return if (this.projection.isEmpty()) schema else schema.project(this.projection)
    }

    override fun toString(): String = "Scan: datasource=${this.dataSource.name}, projection=${this.projection}"
}
