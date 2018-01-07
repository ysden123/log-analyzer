package com.stulsoft.log.analyzer.parser

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Yuriy Stul.
  */
class LineTest extends FlatSpec with Matchers {

  behavior of "Line"

  "parse" should "return Line instance" in {
    val line = "2017-11-09 08:01:31,857 [http-bio-8080-exec-167] [DEBUG] [com.test.class1:1234] - msg 1"
    val result = Line.parse(line)
    result.isDefined shouldBe true
  }

}
