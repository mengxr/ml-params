package ml

import ml.param.{ParamMap, Params}
import sql.{Schema, DataFrame}

trait PipelineStage extends Params[PipelineStage] {

  def transformSchema(schema: Schema): Schema = ???
}

abstract class Transformer extends PipelineStage with Params[Transformer] {

  def transform(dataset: DataFrame): DataFrame = ???

  def transform(dataset: DataFrame, extra: ParamMap): DataFrame = {
    copy(extra).transform(dataset)
  }
}

abstract class Estimator[M <: Model[M]] extends PipelineStage with Params[Estimator[M]] {

  def fit(dataset: DataFrame): M = ???

  def fit(dataset: DataFrame, extra: ParamMap): M = {
    val dup = copy(extra)
    dup.fit(dataset)
  }
}

abstract class Model[Self <: Model[Self]] extends Transformer with Params[Model[Self]] {

  @transient val estimator: Estimator[Self] = ???

  @transient val trainingDataset: DataFrame = ???
}

class Pipeline extends Estimator[PipelineModel] {

  override def uid: String = ???
}

class PipelineModel extends Model[PipelineModel] {

  override def uid: String = ???
}
