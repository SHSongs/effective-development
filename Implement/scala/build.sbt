ThisBuild / scalaVersion     := "3.2.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "algorithm",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.5",
      "dev.zio" %% "zio-test" % "2.0.5" % Test,
      "org.scalatest" %% "scalatest" % "3.2.15" % "test"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
