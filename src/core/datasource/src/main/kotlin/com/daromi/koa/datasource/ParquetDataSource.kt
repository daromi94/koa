package com.daromi.koa.datasource

import com.daromi.koa.datatypes.RecordBatch
import com.daromi.koa.datatypes.Schema

class ParquetDataSource(
    val filePath: String,
) : DataSource {
    override val schema: Schema = TODO("Not yet implemented")

    override fun scan(projection: List<String>): Sequence<RecordBatch> {
        TODO("Not yet implemented")
    }
}
