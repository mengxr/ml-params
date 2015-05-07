package param

import util.Identifiable

class Param[T](val parent: Params[_], val name: String, val doc: String) {
  def validate(value: T): Unit = {}
}

case class ParamPair[T](param: Param[T], value: T) {
  param.validate(value)
}

class ParamMap {
  def put[T](param: Param[T], value: T): this.type = ???
  def get[T](parent: Param[T]): Option[T] = ???
  def ++(other: ParamMap): ParamMap = ???
}

object ParamMap {
  def empty: ParamMap = ???
}

trait Params[+Self <: Params[Self]] extends Identifiable {

  val params: Array[Param[_]] = ???

  def hasParam(name: String): Boolean = ???

  def getParam(name: String): Param[_] = ???

  private val defaultParamMap: ParamMap = ParamMap.empty

  private val paramMap: ParamMap = ParamMap.empty

  final def getDefault[T](param: Param[T]): Option[T] = {
    defaultParamMap.get(param)
  }

  protected final def setDefault[T](param: Param[T], value: T): Self = {
    defaultParamMap.put(param, value)
    this.asInstanceOf[Self]
  }

  protected final def setDefault(paramPairs: ParamPair[_]*): Self = {
    paramPairs.foreach { p =>
      setDefault(p.param.asInstanceOf[Param[Any]], p.value)
    }
    this.asInstanceOf[Self]
  }

  protected final def getOrDefault[T](param: Param[T]): T = {
    get(param).orElse(getDefault(param)).get
  }

  def get[T](param: Param[T]): Option[T] = {
    shouldOwn(param)
    paramMap.get(param)
  }

  protected final def set[T](param: Param[T], value: T): Self = {
    shouldOwn(param)
    paramMap.put(param, value)
    this.asInstanceOf[Self]
  }

  def extractParamMap(extraParamMap: ParamMap): ParamMap = {
    defaultParamMap ++ paramMap ++ extraParamMap
  }

  private def shouldOwn(param: Param[_]): Unit = {
    require(param == getParam(param.name))
  }

  def copyWith(extra: ParamMap): Self = ???

  protected def $[T](param: Param[T]): T = getOrDefault(param)
}
