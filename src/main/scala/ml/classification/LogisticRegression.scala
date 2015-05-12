package ml.classification

import ml.{Estimator, Model}
import ml.param.Params
import ml.param.shared.{HasFeaturesCol, HasMaxIter, HasRegParam}
import sql.DataFrame

private[classification]
trait LogisticRegressionBase extends Params with HasFeaturesCol

class LogisticRegression extends Estimator[LogisticRegressionModel]
  with LogisticRegressionBase with HasMaxIter with HasRegParam {

  override def fit(dataset: DataFrame): LogisticRegressionModel = ???

  override def uid: String = ???

  override def setMaxIter(value: Int): this.type = super.setMaxIter(value)

  override def setRegParam(value: Double): this.type = super.setRegParam(value)

  override def setFeaturesCol(value: String): this.type = super.setFeaturesCol(value)
}

class LogisticRegressionModel extends Model[LogisticRegressionModel] with LogisticRegressionBase {

  override def transform(dataset: DataFrame): DataFrame = ???

  override def uid: String = ???

  override def setFeaturesCol(value: String): this.type = super.setFeaturesCol(value)
}
