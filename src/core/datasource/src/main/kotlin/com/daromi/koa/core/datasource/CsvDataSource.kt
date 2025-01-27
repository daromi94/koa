package com.daromi.koa.core.datasource

import com.daromi.koa.core.datatypes.RecordBatch
import com.daromi.koa.core.datatypes.Schema

class CsvDataSource(
    val filePath: String,
) : DataSource {
    override val schema: Schema = TODO("Not yet implemented")

    override fun scan(projection: List<String>): Sequence<RecordBatch> = TODO("Not yet implemented")
}
