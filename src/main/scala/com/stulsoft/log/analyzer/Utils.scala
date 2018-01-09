package com.stulsoft.log.analyzer

import java.io.{File, FileNotFoundException}

import scala.util.{Failure, Try}

/**
  * @author Yuriy Stul.
  */
object Utils {
  /**
    * Returns a full path to file with specified name
    *
    * @param name the file name (in resource or in file system)
    * @return the full path to file with specified name
    */
  def sourceFileName(name: String): Try[String] = {
    try {
      if (getClass.getClassLoader.getResourceAsStream(name) != null) {
        val file = new File(getClass.getClassLoader.getResource(name).toURI)
        if (file.exists())
          Try(file.getAbsolutePath)
        else
          Failure(new FileNotFoundException(s"File with name $name doesn't exist"))
      } else {
        val file = new File(name)
        if (file.exists())
          Try(file.getAbsolutePath)
        else
          Failure(new FileNotFoundException(s"File with name $name doesn't exist"))
      }
    }
    catch {
      case e: Exception => Failure(e)
    }
  }
}
