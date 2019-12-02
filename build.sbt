name := "scala1"
ThisBuild / organization := "cx.prutser"

version := "0.1"

scalaVersion := "2.13.1"

lazy val scala1 = (project in file("."))
  .settings(
    name := "Scala1",
    libraryDependencies += "org.jsoup" % "jsoup" % "1.12.1",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test,
  )
