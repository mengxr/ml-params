package ml

import ml.param.{ParamMap, Params}
import sql.{Schema, DataFrame}

trait PipelineStage extends Params {

  def transformSchema(schema: Schema): Schema = ???

  override def copy(extra: ParamMap): PipelineStage = ???
}

abstract class Transformer extends PipelineStage {

  override def copy(extra: ParamMap): Transformer = ???

  def transform(dataset: DataFrame): DataFrame = ???

  def transform(dataset: DataFrame, extra: ParamMap): DataFrame = {
    copy(extra).transform(dataset)
  }
}

abstract class Estimator[M <: Model[M]] extends PipelineStage {

  def fit(dataset: DataFrame): M = ???

  def fit(dataset: DataFrame, extra: ParamMap): M = {
    val dup = copy(extra)
    dup.fit(dataset)
  }

  override def copy(extra: ParamMap): Estimator[M] = super.copy(extra).asInstanceOf[Estimator[M]]
}

abstract class Model[Self <: Model[Self]] extends Transformer {

  @transient val estimator: Estimator[Self] = ???

  @transient val trainingDataset: DataFrame = ???

  override def copy(extra: ParamMap): Self = super.copy(extra).asInstanceOf[Self]
}

class Pipeline extends Estimator[PipelineModel] {

  override def uid: String = ???

  override def copy(extra: ParamMap): Pipeline = super.copy(extra).asInstanceOf[Pipeline]
}

class PipelineModel extends Model[PipelineModel] {

  override def uid: String = ???
}
