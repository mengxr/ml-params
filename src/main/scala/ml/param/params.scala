package ml.param

import scala.annotation.varargs

import ml.util.Identifiable

class Param[T](val parentUID: String, val name: String, val doc: String) {

  def this(parent: Identifiable, name: String, doc: String) = this(parent.uid, name, doc)

  override def toString: String = s"${parentUID}__$name"

  override def equals(obj: Any): Boolean = {
    obj match {
      case p: Param[_] => (parentUID == p.parentUID) && (name == p.name)
      case _ => false
    }
  }

  override def hashCode: Int = toString.##

  def w(value: T): ParamPair[T] = this -> value

  def ->(value: T): ParamPair[T] = ???

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

  def empty: ParamMap = new ParamMap
}

trait Params[+Self <: Params[Self]] extends Identifiable {

  lazy val params: Array[Param[_]] = ???

  def hasParam(name: String): Boolean = ???

  def getParam(name: String): Param[_] = ???

  private val defaultParamMap: ParamMap = ParamMap.empty

  private val paramMap: ParamMap = ParamMap.empty

  final def getDefault[T](param: Param[T]): Option[T] = {
    defaultParamMap.get(param)
  }

  protected final def setDefault[T](param: Param[T], value: T): this.type = {
    defaultParamMap.put(param, value)
    this
  }

  @varargs
  protected final def setDefault(paramPairs: ParamPair[_]*): this.type = {
    paramPairs.foreach { p =>
      setDefault(p.param.asInstanceOf[Param[Any]], p.value)
    }
    this
  }

  protected final def getOrDefault[T](param: Param[T]): T = {
    get(param).orElse(getDefault(param)).get
  }

  def get[T](param: Param[T]): Option[T] = {
    shouldOwn(param)
    paramMap.get(param)
  }

  protected final def set[T](param: Param[T], value: T): this.type = {
    shouldOwn(param)
    paramMap.put(param, value)
    this
  }

  def extractParamMap(extra: ParamMap): ParamMap = {
    defaultParamMap ++ paramMap ++ extra
  }

  private def shouldOwn(param: Param[_]): Unit = {
    require(param == getParam(param.name))
  }

  def copy(extra: ParamMap): Self = ???

  protected final def $[T](param: Param[T]): T = getOrDefault(param)
}
