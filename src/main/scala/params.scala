import scala.collection.mutable

class Param(val parent: Params, val name: String, val doc: String) {

  def ->(value: Int): ParamPair = ParamPair(this, value)

  def w(value: Int): ParamPair = this -> value
}

case class ParamPair(param: Param, value: Int)

trait Params {

  private val defaultValues: mutable.Map[Param, Int] = mutable.Map.empty

  protected final def getDefault(param: Param): Option[Int] = defaultValues.get(param)

  protected final def setDefault(param: Param, value: Int): this.type = {
    defaultValues(param) = value
    this
  }

  protected final def setDefault(paramPairs: ParamPair*): this.type = {
    paramPairs.foreach { p =>
      setDefault(p.param, p.value)
    }
    this
  }

  protected val paramMap: mutable.Map[Param, Int] = mutable.Map.empty

  protected final def getOrDefault(param: Param): Int = {
    paramMap.get(param).orElse(getDefault(param)).get
  }

  protected final def set(param: Param, value: Int): this.type = {
    require(param.parent == this)
    paramMap(param) = value
    this
  }

  def getParamMap(extraParamMap: mutable.Map[Param, Int]): mutable.Map[Param, Int] =
    defaultValues ++ paramMap ++ extraParamMap
  }
}

trait HasMaxIter extends Params {

  val maxIter: Param = new Param(this, "maxIter", "maximum number of iterations")

  def getMaxIter: Int = getOrDefault(maxIter)
}

trait HasRegParam extends Params {

  val regParam: Param = new Param(this, "regParam", "regularization parameter")

  def getRegParam: Int = getOrDefault(regParam)
}

abstract class LogisticRegression extends Params with HasMaxIter with HasRegParam {

  def setMaxIter(value: Int): this.type = set(maxIter, value)

  def setRegParam(value: Int): this.type = set(regParam, value)

  setDefault(maxIter -> 10, regParam -> 1)

  def fit(df: AnyRef, extraParamMap: mutable.Map[Param, Int]): LogisticRegressionModel = {

    ???
  }
}

class LogisticRegressionModel extends Params with HasMaxIter with HasRegParam {

}
