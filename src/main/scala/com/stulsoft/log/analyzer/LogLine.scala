package com.stulsoft.log.analyzer

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
  * @author Yuriy Stul.
  */
case class LogLine(date: LocalDateTime, thread: String, level: String, message: String)

object LogLine {
  /**
    * Parses a log line
    *
    * @param line the log line
    * @return the Option with instance of the Line class
    */
  def parse(line: String): Option[LogLine] = {
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
}
