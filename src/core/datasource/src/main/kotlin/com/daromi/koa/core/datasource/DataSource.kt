package com.daromi.koa.core.datasource

import com.daromi.koa.core.datatypes.RecordBatch
import com.daromi.koa.core.datatypes.Schema

interface DataSource {
    val schema: Schema

    fun scan(projection: List<String>): Sequence<RecordBatch>
}
