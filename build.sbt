name := """beverly-api"""
organization := "com.beverly-spa"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += javaJdbc
libraryDependencies += evolutions
libraryDependencies += "org.jdbi" % "jdbi" % "2.78"
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.1"
libraryDependencies += "software.amazon.awssdk" % "sdk-core" % "2.17.90"
libraryDependencies += "software.amazon.awssdk" % "dynamodb" % "2.17.90"
libraryDependencies += "software.amazon.awssdk" % "bom" % "2.17.90" % "runtime" pomOnly()
