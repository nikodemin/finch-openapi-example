val finchVersion = "0.34.0"
val circeVersion = "0.14.5"
val scalatestVersion = "3.2.15"

lazy val root = (project in file("."))
  .settings(
    organization := "com.github.nikodemin",
    name := "finch-openapi-example",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      "com.github.finagle" %% "finch-core"  % finchVersion,
      "com.github.finagle" %% "finch-circe"  % finchVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "org.scalatest"      %% "scalatest"    % scalatestVersion % "test"
    )
  )