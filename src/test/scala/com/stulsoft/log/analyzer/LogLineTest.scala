package com.stulsoft.log.analyzer

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Yuriy Stul.
  */
class LogLineTest extends FlatSpec with Matchers {
  val parser1: (String) => Option[LogLine] = line => {
    val items = line.split(" ")
    if (items.length < 7)
      None
    else {
      try {
        val date = LocalDateTime.parse(items(0) + " " + items(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS"))
        val thread = items(2).replace("[", "").replace("]", "")
        val level = items(3).replace("[", "").replace("]", "")
        val message = items.drop(6).mkString(" ")
        Some(LogLine(date, thread, level, message))
      }
      catch {
        case _: Throwable => None
      }
    }
  }

  behavior of "LogLine"

  "parse(line)" should "return LogLine" in {
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

  it should "prevent incorrect line" in {
    val line1 = "2017123-11-09 08:01:31,857 [http-bio-8080-exec-167] [DEBUG] [com.test.class1:1234] - msg 1"
    val result1 = LogLine.parse(line1)
    result1.isDefined shouldBe false

    val line2 = " error text"
    val result2 = LogLine.parse(line2)
    result2.isDefined shouldBe false
  }

  "parse(line,f)" should "return LogLine (lambda)" in {
    val line = "2017-11-09 08:01:31,857 [http-bio-8080-exec-167] [DEBUG] [com.test.class1:1234] - msg 1"
    val result = LogLine.parse(line, l => {
      val items = l.split(" ")
      if (items.length < 7)
        None
      else {
        try {
          val date = LocalDateTime.parse(items(0) + " " + items(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS"))
          val thread = items(2).replace("[", "").replace("]", "")
          val level = items(3).replace("[", "").replace("]", "")
          val message = items.drop(6).mkString(" ")
          Some(LogLine(date, thread, level, message))
        }
        catch {
          case _: Throwable => None
        }
      }
    })

    result.isDefined shouldBe true
    val theLine = result.get
    theLine.date.getYear shouldBe 2017
    theLine.date.getMonth.getValue shouldBe 11
    theLine.date.getDayOfMonth shouldBe 9

    theLine.thread shouldBe "http-bio-8080-exec-167"
    theLine.level shouldBe "DEBUG"
    theLine.message shouldBe "msg 1"
  }

  it should "should return LogLine (function)" in {
    val line = "2017-11-09 08:01:31,857 [http-bio-8080-exec-167] [DEBUG] [com.test.class1:1234] - msg 1"
    val result = LogLine.parse(line, parser1)

    result.isDefined shouldBe true
    val theLine = result.get
    theLine.date.getYear shouldBe 2017
    theLine.date.getMonth.getValue shouldBe 11
    theLine.date.getDayOfMonth shouldBe 9

    theLine.thread shouldBe "http-bio-8080-exec-167"
    theLine.level shouldBe "DEBUG"
  }
}
