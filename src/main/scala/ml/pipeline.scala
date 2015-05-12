package ml

import ml.param.{ParamMap, Params}
import sql.DataFrame

trait PipelineStage extends Params {

  // def transformSchema(schema: Schema): Schema = ???
}

abstract class Transformer extends PipelineStage {

  def transform(dataset: DataFrame): DataFrame

  def transform(dataset: DataFrame, extra: ParamMap): DataFrame = {
    copy(extra).transform(dataset)
  }

  override def copy(extra: ParamMap): Transformer = ???
}

abstract class Estimator[M <: Model[M]] extends PipelineStage {

  def fit(dataset: DataFrame): M

  def fit(dataset: DataFrame, extra: ParamMap): M = {
    val dup = copy(extra)
    dup.fit(dataset)
  }

  override def copy(extra: ParamMap): Estimator[M] = ???
}

abstract class Model[M <: Model[M]] extends Transformer {
  override def copy(extra: ParamMap): Model[M] = ???
}

class Pipeline extends Estimator[PipelineModel] {

  override def uid: String = "pipeline"

  override def fit(dataset: DataFrame): PipelineModel = ???

  override def copy(extra: ParamMap): Pipeline = ???
}

class PipelineModel extends Model[PipelineModel] {

  override def uid: String = ???

  override def transform(dataset: DataFrame): DataFrame = ???

  override def copy(extra: ParamMap): PipelineModel = ???
}
