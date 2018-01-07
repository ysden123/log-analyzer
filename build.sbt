import sbt.Keys.libraryDependencies

lazy val sparkVersion = "2.2.0"
lazy val scalaLoggingVersion = "3.5.0"
lazy val commonIoVersion = "1.3.2"
lazy val scalaTestVersion = "3.0.1"

lazy val commonSettings = Seq(
  organization := "com.stulsoft.pspark",
  version := "1.1.2",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps"),
  libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion
  )
)

resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Repo at github.com/ankurdave/maven-repo" at "https://github.com/ankurdave/maven-repo/raw/master"

lazy val logAnalyzer = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "log-analyzer"
  )
  .settings(
    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/org.apache.commons/commons-io
      "org.apache.commons" % "commons-io" % commonIoVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
    )
  )