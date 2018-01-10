package com.stulsoft.log.analyzer

import scala.io.StdIn

/**
  * @author Yuriy Stul.
  */
object Main extends App {
  val fileName = StdIn.readLine("Enter file name: ")
  if (!fileName.isEmpty) {
    Analyzer.analyze(fileName)
  }
}
