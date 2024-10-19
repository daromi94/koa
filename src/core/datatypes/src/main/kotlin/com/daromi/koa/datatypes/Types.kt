package com.daromi.koa.datatypes

import org.apache.arrow.vector.types.FloatingPointPrecision
import org.apache.arrow.vector.types.pojo.ArrowType

object Types {
  val Boolean = ArrowType.Bool()

  // Signed integers
  val Int8  = ArrowType.Int(8,  true)
  val Int16 = ArrowType.Int(16, true)
  val Int32 = ArrowType.Int(32, true)
  val Int64 = ArrowType.Int(64, true)

  // Unsigned integers
  val UInt8  = ArrowType.Int(8,  false)
  val UInt16 = ArrowType.Int(16, false)
  val UInt32 = ArrowType.Int(32, false)
  val UInt64 = ArrowType.Int(64, false)

  // Floating-point numbers
  val Float  = ArrowType.FloatingPoint(FloatingPointPrecision.SINGLE)
  val Double = ArrowType.FloatingPoint(FloatingPointPrecision.DOUBLE)

  val String = ArrowType.Utf8()
}
