val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "sc3t",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

      libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
      libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )