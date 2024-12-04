ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "json",  // Set the name of the project

    // Add dependencies
    libraryDependencies ++= Seq(

      // Slick framework for working with databases
      "com.typesafe.slick" %% "slick" % "3.5.0",             // Slick framework
      "mysql" % "mysql-connector-java" % "8.0.33",            // MySQL JDBC driver for Slick to connect to MySQL database
      "org.slf4j" % "slf4j-api" % "2.0.0",                    // SLF4J API for logging
      "ch.qos.logback" % "logback-classic" % "1.2.10",         // Logback for logging implementation

      // JSON processing using Circe
      "io.circe" %% "circe-core" % "0.14.3",                  // Circe core library
      "io.circe" %% "circe-generic" % "0.14.3",               // Circe generic for automatic derivation
      "io.circe" %% "circe-parser" % "0.14.3",                // Circe parser for parsing JSON

      // Play Framework dependencies
      "com.typesafe.play" %% "play" % "2.9.0",                // Play Framework core library (optional if you're using Play)
      "com.typesafe.play" %% "play-json" % "2.10.0"           // Play JSON library for handling JSON serialization/deserialization
    )
  )
