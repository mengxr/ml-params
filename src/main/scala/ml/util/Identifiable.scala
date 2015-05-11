package ml.util

trait Identifiable {
  /** Unique id for this instance and its derivatives. */
  def uid: String
}
