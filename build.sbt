name := """beverly-clientas"""
organization := "com.beverly-spa"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "software.amazon.awssdk" % "sdk-core" % "2.17.90"
libraryDependencies += "software.amazon.awssdk" % "dynamodb" % "2.17.90"
libraryDependencies += "software.amazon.awssdk" % "sqs" % "2.17.99"
libraryDependencies += "software.amazon.awssdk" % "bom" % "2.17.90" % "runtime" pomOnly()
