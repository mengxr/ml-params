package classification

import ml.DataFrame
import param.{ParamMap, Params}
import param.shared.{HasMaxIter, HasRegParam}

trait LogisticRegressionBase extends Params[LogisticRegressionBase] with HasMaxIter with HasRegParam

abstract class LogisticRegression extends LogisticRegressionBase with Params[LogisticRegression] {

  def fit(dataset: DataFrame): LogisticRegressionModel = ???
}

class LogisticRegressionModel extends LogisticRegressionBase with Params[LogisticRegressionModel] {
  override def copyWith(extra: ParamMap): LogisticRegressionModel = ???
}
