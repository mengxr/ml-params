package ml.classification

import ml.{Estimator, Model}
import ml.param.{ParamMap, Params}
import ml.param.shared.{HasMaxIter, HasRegParam}
import sql.DataFrame

trait LogisticRegressionBase extends Params with HasMaxIter with HasRegParam

class LogisticRegression extends Estimator[LogisticRegressionModel] with LogisticRegressionBase {

  override def fit(dataset: DataFrame): LogisticRegressionModel = ???

  override def uid: String = ???
}

class LogisticRegressionModel extends Model[LogisticRegressionModel] with LogisticRegressionBase {

  override def transform(dataset: DataFrame): DataFrame = ???

  override def copy(extra: ParamMap): LogisticRegressionModel = ???

  /** Unique id for this instance and its derivatives. */
  override def uid: String = ???
}
