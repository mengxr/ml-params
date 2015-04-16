package param

import scala.collection.mutable

class Param(val parent: Params, val name: String, val doc: String) {

  def ->(value: Int): ParamPair = ParamPair(this, value)

  def w(value: Int): ParamPair = this -> value
}

case class ParamPair(param: Param, value: Int)

type ParamMap = mutable.Map[Param, Int]
val ParamMap = mutable.Map

trait Params {

  private val defaultParamMap: ParamMap = ParamMap.empty

  protected final def getDefault(param: Param): Option[Int] = defaultParamMap.get(param)

  protected final def setDefault(param: Param, value: Int): this.type = {
    defaultParamMap(param) = value
    this
  }

  protected final def setDefault(paramPairs: ParamPair*): this.type = {
    paramPairs.foreach { p =>
      setDefault(p.param, p.value)
    }
    this
  }

  protected val paramMap: ParamMap = mutable.Map.empty

  protected final def getOrDefault(param: Param): Int = {
    paramMap.get(param).orElse(getDefault(param)).get
  }

  protected final def set(param: Param, value: Int): this.type = {
    require(param.parent == this)
    paramMap(param) = value
    this
  }

  def extractParamMap(extraParamMap: ParamMap): ParamMap = {
    defaultParamMap ++ paramMap ++ extraParamMap
  }
}
