package ml.param.shared

import ml.param.{DoubleParam, IntParam, Param, Params}

private[ml] trait HasFeaturesCol extends Params[HasFeaturesCol] {

  final val featuresCol: Param[String] = new Param[String](this, "featuresCol", "features column name")

  final def getFeaturesCol: String = $(featuresCol)

  protected def setFeaturesCol(value: String): this.type = set(featuresCol -> value)
}

private[ml] trait HasMaxIter extends Params[HasMaxIter] {

  final val maxIter: IntParam = new IntParam(this, "maxIter", "max number of iterations")

  final def getMaxIter: Int = $(maxIter)

  protected def setMaxIter(value: Int): this.type = set(maxIter -> value)
}

private[ml] trait HasRegParam extends Params[HasMaxIter] {

  final val regParam: DoubleParam = new DoubleParam(this, "regParam", "regularization parameter")

  final def getRegParam: Double = $(regParam)

  protected def setRegParam(value: Double): this.type = set(regParam -> value)
}
