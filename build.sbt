name := "scala-exercises"

version := "0.1"

scalaVersion := "2.13.6"

//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

// libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"

// from: https://www.scalatest.org/user_guide/using_scalatest_with_sbt
logBuffered in Test := false
