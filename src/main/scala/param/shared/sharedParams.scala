package param.shared

import param.Param

trait HasMaxIter extends Params {

  val maxIter: Param = new Param(this, "maxIter", "maximum number of iterations")

  def getMaxIter: Int = getOrDefault(maxIter)
}

trait HasRegParam extends Params {

  val regParam: Param = new Param(this, "regParam", "regularization parameter")

  def getRegParam: Int = getOrDefault(regParam)
}
