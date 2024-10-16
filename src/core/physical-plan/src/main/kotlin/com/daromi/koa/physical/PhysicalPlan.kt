package com.daromi.koa.physical

import com.daromi.koa.datatypes.RecordBatch
import javax.xml.validation.Schema

class PhysicalPlan(
    private val root: PhysicalOperator,
)

interface PhysicalOperator {
    val schema: Schema

    val children: List<PhysicalOperator>

    fun execute(): Sequence<RecordBatch>
}
