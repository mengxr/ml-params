package ml.classification

import ml.param.{ParamMap, Params}
import ml.param.shared.{HasMaxIter, HasRegParam}
import sql.DataFrame

trait LogisticRegressionBase extends Params[LogisticRegressionBase] with HasMaxIter with HasRegParam

abstract class LogisticRegression extends LogisticRegressionBase with Params[LogisticRegression] {

  def fit(dataset: DataFrame): LogisticRegressionModel = ???
}

class LogisticRegressionModel extends LogisticRegressionBase with Params[LogisticRegressionModel] {
  override def copy(extra: ParamMap): LogisticRegressionModel = ???
}