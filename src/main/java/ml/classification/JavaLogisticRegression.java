package ml.classification;

import ml.Estimator;
import ml.param.DoubleParam;
import ml.param.IntParam;
import ml.param.Param;

public class JavaLogisticRegression extends Estimator<JavaLogisticRegressionModel> {

  private IntParam _maxIter = new IntParam(this, "maxIter", "max number of iterations");
  public IntParam maxIter() { return _maxIter; }
  public JavaLogisticRegression setMaxIter(int value) {
    set(_maxIter.w(value));
    return this;
  }

  private DoubleParam _regParam = new DoubleParam(this, "regParam", "regularization parameter");
  public DoubleParam regParam() { return _regParam; }
  public JavaLogisticRegression setRegParam(double value) {
    set(_regParam.w(value));
    return this;
  }

  private Param<String> _featuresCol = new Param<String>(this, "featuresCol", "features column name");
  public Param<String> featuresCol() { return _featuresCol; }
  public JavaLogisticRegression setFeaturesCol(String value) {
    set(_featuresCol.w(value));
    return this;
  }

  @Override
  public String uid() {
    return null;
  }
}
