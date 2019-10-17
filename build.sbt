name := "SparkTask1a"

version := "0.1"

scalaVersion := "2.11.12"

mainClass in (Compile, packageBin) := Some("by.epam.bigdata.TopCouplesHotels")

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"