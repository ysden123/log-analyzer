package com.stulsoft.log.analyzer

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Yuriy Stul.
  */
class LogLineTest extends FlatSpec with Matchers {

  behavior of "Line"

  "parse" should "return Line instance" in {
    val line = "2017-11-09 08:01:31,857 [http-bio-8080-exec-167] [DEBUG] [com.test.class1:1234] - msg 1"
    val result = LogLine.parse(line)
    result.isDefined shouldBe true
    val theLine = result.get
    theLine.date.getYear shouldBe 2017
    theLine.date.getMonth.getValue shouldBe 11
    theLine.date.getDayOfMonth shouldBe 9

    theLine.thread shouldBe "http-bio-8080-exec-167"
    theLine.level shouldBe "DEBUG"
    theLine.message shouldBe "msg 1"
  }

  it should "prevent incorrect line" in{
    val line1 = "2017123-11-09 08:01:31,857 [http-bio-8080-exec-167] [DEBUG] [com.test.class1:1234] - msg 1"
    val result1 = LogLine.parse(line1)
    result1.isDefined shouldBe false

    val line2 = " error text"
    val result2 = LogLine.parse(line2)
    result2.isDefined shouldBe false
  }

}
