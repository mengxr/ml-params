package ml

import ml.param.{ParamMap, Params}

trait PipelineStage[+Self <: PipelineStage[Self]] extends Params[Self]

abstract class Transformer[+Self] extends PipelineStage[Transformer[Self]] {

  def transform(dataset: DataFrame): DataFrame = ???

  def transform(dataset: DataFrame, extra: ParamMap): DataFrame = {
    val dup = copy(extra)
    dup.transform(dataset)
  }
}

abstract class Estimator[+Self, M <: Model[M]] extends PipelineStage[Estimator[Self, M]] {
  def fit(dataset: DataFrame): M = ???
  def fit(dataset: DataFrame, extra: ParamMap): M = {
    val dup = copy(extra)
    dup.fit(dataset)
  }
}

abstract class Model[+Self] extends Transformer[Model[Self]] {
  @transient val estimator: Estimator[_, _] = ???
  @transient val trainingDataset: DataFrame = ???
}

class Pipeline extends Estimator[Pipeline, PipelineModel]

class PipelineModel extends Model[PipelineModel]
