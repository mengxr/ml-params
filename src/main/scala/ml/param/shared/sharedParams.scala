package ml.param.shared

import ml.param.{Param, Params}

private[ml] trait HasMaxIter extends Params[HasMaxIter] {

  val maxIter: Param[Int] = new Param[Int](this, "maxIter", "max number of iterations")

  def getMaxIter: Int = $(maxIter)

  protected def setMaxIter(value: Int): this.type = set(maxIter, value)
}

private[ml] trait HasRegParam extends Params[HasMaxIter] {

  val regParam: Param[Double] = new Param[Double](this, "regParam", "regularization parameter")

  def getRegParam: Double = $(regParam)

  protected def setRegParam(value: Double): this.type = set(regParam, value)
}
