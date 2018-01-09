package com.stulsoft.log.analyzer

import org.apache.log4j.Logger

/**
  * @author Yuriy Stul.
  */
object Analyzer {
  val logger = Logger.getLogger(getClass.getName)

  def analyze(logFileName: String): Unit = {
        logger.info("==>analyze")
        logger.info("<==analyze")
  }
}
