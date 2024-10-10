package com.daromi.koa.datasource

import com.daromi.koa.datatypes.RecordBatch
import com.daromi.koa.datatypes.Schema

class CsvDataSource(
    val filePath: String,
) : DataSource {
    override val schema: Schema = TODO("Not yet implemented")

    override fun scan(projection: List<String>): Iterator<RecordBatch> {
        TODO("Not yet implemented")
    }
}
