package com.daromi.koa.datasource

import com.daromi.koa.datatypes.Batch
import com.daromi.koa.datatypes.Schema

interface DataSource {
    val schema: Schema

    fun scan(projection: List<String>): Iterator<Batch>
}
