package ml.param

import scala.collection.mutable

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

class IntParam(parentUID: String, name: String, doc: String) extends Param[Int](parentUID, name, doc) {
  def this(parent: Identifiable, name: String, doc: String) = this(parent.uid, name, doc)

  override def w(value: Int) = this -> value
}

class DoubleParam(parentUID: String, name: String, doc: String) extends Param[Double](parentUID, name, doc) {
  def this(parent: Identifiable, name: String, doc: String) = this(parent.uid, name, doc)

  override def w(value: Double) = this -> value
}

case class ParamPair[T](param: Param[T], value: T) {
  param.validate(value)
}

class ParamMap {

  private val map = mutable.Map.empty[Param[Any], Any]

  // def put[T](param: Param[T], value: T): this.type = put(param -> value)

  def put(paramPair: ParamPair[_]): this.type = {
    map.put(paramPair.param.asInstanceOf[Param[Any]], paramPair.value)
    this
  }

  def get[T](parent: Param[T]): Option[T] = ???

  def ++(other: ParamMap): ParamMap = ???
}

object ParamMap {

  def empty: ParamMap = new ParamMap
}

trait Params extends Identifiable {

  lazy val params: Array[Param[_]] = ???

  def hasParam(name: String): Boolean = ???

  def getParam(name: String): Param[_] = ???

  private val defaultParamMap: ParamMap = ParamMap.empty

  private val paramMap: ParamMap = ParamMap.empty

  final def getDefault[T](param: Param[T]): Option[T] = {
    defaultParamMap.get(param)
  }

  protected final def setDefault(paramPair: ParamPair[_]): this.type = {
    defaultParamMap.put(paramPair)
    this
  }

  protected final def getOrDefault[T](param: Param[T]): T = {
    get(param).orElse(getDefault(param)).get
  }

  def get[T](param: Param[T]): Option[T] = {
    shouldOwn(param)
    paramMap.get(param)
  }

  // protected final def set[T](param: Param[T], value: T): this.type = set(param -> value)

  protected final def set(paramPair: ParamPair[_]): this.type = {
    shouldOwn(paramPair.param)
    paramMap.put(paramPair)
    this
  }

  def extractParamMap(extra: ParamMap): ParamMap = {
    defaultParamMap ++ paramMap ++ extra
  }

  private def shouldOwn(param: Param[_]): Unit = {
    require(param == getParam(param.name))
  }

  def copy(extra: ParamMap): Params = ???

  protected final def $[T](param: Param[T]): T = getOrDefault(param)
}
