package com.stulsoft.log.analyzer

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Yuriy Stul.
  */
class AnalyzerTest extends FlatSpec with Matchers {

  behavior of "Analyzer"

  "analyze" should "handle invalid file name" in {
    Analyzer.analyze("error.txt")
  }

  it should "handle valid file name" in {
    Analyzer.analyze("example1.log")
  }

}
