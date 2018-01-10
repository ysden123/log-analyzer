package com.stulsoft.log.analyzer

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Yuriy Stul.
  */
class UtilsTest extends FlatSpec with Matchers {

  behavior of "Utils"

  "sourceFileName" should "return path to file in resource" in {
    val path = Utils.sourceFileName("example1.log")
    path.isSuccess shouldBe true
  }

  it should "return path to file in file system" in {
    val path = Utils.sourceFileName("build.sbt")
    path.isSuccess shouldBe true
  }

}
