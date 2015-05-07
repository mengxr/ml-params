package param.shared

import param._

trait HasMaxIter extends Params[HasMaxIter] {
  val maxIter: Param[Int] = new Param[Int](this, "maxIter", "max number of iterations")
  def getMaxIter: Int = $(maxIter)
}

trait HasRegParam extends Params[HasMaxIter] {
  val regParam: Param[Double] = new Param[Double](this, "regParam", "regularization parameter")
  def getRegParam: Double = $(regParam)
}
