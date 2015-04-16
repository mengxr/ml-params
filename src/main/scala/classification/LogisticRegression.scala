package classification

import param._
import param.shared._

abstract class LogisticRegression extends Params with HasMaxIter with HasRegParam {

  def setMaxIter(value: Int): this.type = set(maxIter, value)

  def setRegParam(value: Int): this.type = set(regParam, value)

  setDefault(maxIter -> 10, regParam -> 1)

  def fit(df: AnyRef, extraParamMap: ParamMap): LogisticRegressionModel = {

    ???
  }
}

class LogisticRegressionModel extends Params with HasMaxIter with HasRegParam {

}
