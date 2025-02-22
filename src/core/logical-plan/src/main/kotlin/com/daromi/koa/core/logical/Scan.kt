package com.daromi.koa.core.logical

import com.daromi.koa.core.datasource.DataSource
import com.daromi.koa.core.datatypes.Schema

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

    override fun toString(): String = "Scan: datasource=${this.dataSource}, projection=${this.projection}"
}
