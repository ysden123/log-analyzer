package com.stulsoft.log.analyzer

import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Yuriy Stul.
  */
object Analyzer {
  private val logger = Logger.getLogger(getClass.getName)

  def analyze(logFileName: String): Unit = {
    logger.info("==>analyze")
    val conf = new SparkConf().setAppName("Log Analyzer - analyze").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sourceFileNameTry = Utils.sourceFileName(logFileName)
    if (sourceFileNameTry.isSuccess) {
      val sourceFileName = sourceFileNameTry.get
      logger.info(s"Analyzing file $sourceFileName")

      val data = sc.textFile(sourceFileName)
        .map(line => LogLine.parse(line))
        .flatMap(x => x) // Ignores None, maps Some(LogLine) to LogLine
        .cache()

      val count = data.count()
      logger.debug(s"count=$count")

      val errorCount = data.filter(l => l.level == "ERROR").count()
      logger.debug(s"errorCount=$errorCount")

      data
        .map(ll => (ll.level, 1))
        .reduceByKey(_ + _)
        .collect()
        .sortBy(_._1)
        .foreach(logger.debug)
    } else {
      logger.error(s"Failed open $logFileName. Error: ${sourceFileNameTry.failed.get.getMessage}")
    }

    sc.stop()
    logger.info("<==analyze")
  }
}
