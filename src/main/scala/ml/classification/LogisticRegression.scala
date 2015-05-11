package ml.classification

import ml.{Estimator, Model}
import ml.param.{ParamMap, Params}
import ml.param.shared.{HasMaxIter, HasRegParam}
import sql.DataFrame

private[classification]
trait LogisticRegressionBase extends Params[LogisticRegressionBase] with HasMaxIter with HasRegParam

class LogisticRegression extends Estimator[LogisticRegressionModel] with LogisticRegressionBase with Params[LogisticRegression] {

  override def fit(dataset: DataFrame): LogisticRegressionModel = ???

  override def uid: String = ???
}

class LogisticRegressionModel extends Model[LogisticRegressionModel] with LogisticRegressionBase with Params[LogisticRegressionModel] {

  override def transform(dataset: DataFrame): DataFrame = ???

  override def copy(extra: ParamMap): LogisticRegressionModel = ???

  override def uid: String = ???
}
