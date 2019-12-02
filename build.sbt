name := "crawler"
ThisBuild / organization := "cx.prutser"

version := "0.1"

scalaVersion := "2.13.1"

lazy val crawler = (project in file("."))
  .settings(
    name := "crawler",
    libraryDependencies += "org.jsoup" % "jsoup" % "1.12.1",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test,
    assemblyJarName in assembly := "crawler.jar",
  )
