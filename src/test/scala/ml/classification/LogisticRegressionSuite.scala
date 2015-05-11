package ml.classification

import sql.DataFrame

object LogisticRegressionSuite {

  def main(args: Array[String]): Unit = {
    val dataset = new DataFrame
    val lr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.1)
    val model = lr.fit(dataset)
  }
}
