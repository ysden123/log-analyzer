package com.stulsoft.log.analyzer.parser

import java.text.SimpleDateFormat


/**
  * @author Yuriy Stul.
  */
case class Line(date: String, time: String, thread: String, level: String, message: String)

object Line {
  def parse(line: String): Option[Line] = {
    val items = line.split(" ")
    if (items.length < 7)
      None
    else {
      try{
        val sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ddd")
        val d = sdf.parse("2017-11-09 08:01:31,857")
        println(d)
        Some(Line("date", "time", "thread", "level", "message"))
      }
      catch{
        case e:Throwable=>None
      }
    }
  }
}
