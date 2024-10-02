package com.daromi.koa.logical

import com.daromi.koa.datasource.DataSource
import com.daromi.koa.datatypes.Schema

class Scan(
    private val dataSource: DataSource,
    private val projection: List<String>,
) : LogicalPlan {
    override val schema: Schema = deriveSchema()

    override val children: List<LogicalPlan> get() = emptyList()

    private fun deriveSchema(): Schema {
        val schema = this.dataSource.schema

        return if (this.projection.isEmpty()) {
            schema
        } else {
            schema.select(this.projection)
        }
    }

    override fun toString(): String = "Scan: projection=${this.projection}"
}
