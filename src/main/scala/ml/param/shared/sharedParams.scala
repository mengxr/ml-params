package ml.param.shared

import ml.param.{Param, Params}

private[ml] trait HasMaxIter extends Params {

  val maxIter: Param[Int] = new Param[Int](this, "maxIter", "max number of iterations")

  def getMaxIter: Int = $(maxIter)
}

private[ml] trait HasRegParam extends Params {

  val regParam: Param[Double] = new Param[Double](this, "regParam", "regularization parameter")

  def getRegParam: Double = $(regParam)
}
