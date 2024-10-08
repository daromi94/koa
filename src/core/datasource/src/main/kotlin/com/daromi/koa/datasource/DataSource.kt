package com.daromi.koa.datasource

import com.daromi.koa.datatypes.RecordBatch
import com.daromi.koa.datatypes.Schema

interface DataSource {
    val name: String

    val schema: Schema

    fun scan(projection: List<String>): Iterator<RecordBatch>
}
