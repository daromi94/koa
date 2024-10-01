package com.daromi.koa.datasource

import com.daromi.koa.datatypes.Batch
import org.apache.arrow.vector.types.pojo.Schema

interface DataSource {
    val schema: Schema

    fun scan(projection: List<String>): Iterator<Batch>
}
