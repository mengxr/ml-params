package util

import java.util.UUID

trait Identifiable {

  protected val simpleName: String = this.getClass.getSimpleName

  final val uid: String = simpleName + "_" + UUID.randomUUID().toString.take(8)

  override def toString: String = uid
}
