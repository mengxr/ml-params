package ml.classification;

import sql.DataFrame;

public class JavaLogisticRegressionSuite {

  public static void main(String[] args) {
    DataFrame training = new DataFrame();
    LogisticRegression lr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.1);
    LogisticRegressionModel model = lr.fit(training);

    JavaLogisticRegression jlr = new JavaLogisticRegression();
  }
}
