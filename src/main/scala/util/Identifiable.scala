package util

import java.util.UUID

trait Identifiable {

  protected val simpleName: String = this.getClass.getSimpleName

  private var _uid: String = simpleName + "_" + UUID.randomUUID().toString.take(8)

  /**
   * @return uid
   */
  final def uid: String = _uid

  override def toString: String = uid

  protected final def setUID(uid: String): this.type = {
    this._uid = uid
    this
  }
}
