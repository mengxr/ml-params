package ml

import ml.param.{ParamMap, Params}
import sql.DataFrame

trait PipelineStage extends Params[PipelineStage] {

  // def transformSchema(schema: Schema): Schema = ???
}

abstract class Transformer extends PipelineStage with Params[Transformer] {

  def transform(dataset: DataFrame): DataFrame

  def transform(dataset: DataFrame, extra: ParamMap): DataFrame = {
    copy(extra).transform(dataset)
  }
}

abstract class Estimator[M <: Model[M]] extends PipelineStage with Params[Estimator[M]] {

  def fit(dataset: DataFrame): M

  def fit(dataset: DataFrame, extra: ParamMap): M = {
    val dup = copy(extra)
    dup.fit(dataset)
  }
}

abstract class Model[Self <: Model[Self]] extends Transformer with Params[Model[Self]]

class Pipeline extends Estimator[PipelineModel] with Params[Pipeline] {

  override def uid: String = "pipeline"

  override def fit(dataset: DataFrame): PipelineModel = ???
}

class PipelineModel extends Model[PipelineModel] with Params[PipelineModel] {

  override def uid: String = ???

  override def transform(dataset: DataFrame): DataFrame = ???
}
