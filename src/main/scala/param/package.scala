import scala.collection.mutable

package object param {
  type ParamMap = mutable.Map[Param, Int]
  val ParamMap = mutable.Map
}
