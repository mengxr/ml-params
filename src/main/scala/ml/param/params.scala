package ml.param

import scala.annotation.varargs

import ml.util.Identifiable

import scala.collection.mutable

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

  @varargs
  def put(first: ParamPair[_], others: ParamPair[_]*): this.type = {
    (first +: others).foreach { p =>
      map.put(p.param.asInstanceOf[Param[Any]], p.value)
    }
    this
  }

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

  @varargs
  protected final def setDefault(first: ParamPair[_], others: ParamPair[_]*): this.type = {
    (first +: others).foreach { p =>
      defaultParamMap.put(p)
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

  // protected final def set[T](param: Param[T], value: T): this.type = set(param -> value)

  @varargs
  protected final def set(first: ParamPair[_], others: ParamPair[_]*): this.type = {
    (first +: others).foreach { p =>
      shouldOwn(p.param)
      paramMap.put(p)
    }
    this
  }

  def extractParamMap(extra: ParamMap): ParamMap = {
    defaultParamMap ++ paramMap ++ extra
  }

  private def shouldOwn(param: Param[_]): Unit = {
    require(param == getParam(param.name))
  }

  def copy(extra: ParamMap): Self = null.asInstanceOf[Self]

  protected final def $[T](param: Param[T]): T = getOrDefault(param)
}
