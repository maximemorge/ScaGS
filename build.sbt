name := "ScaGS"

version := "1.0"

mainClass in (Compile,run) := Some("ScaGS")
mainClass in assembly := Some("ScaGS")

resolvers += "Artifactory-UCL" at "http://artifactory.info.ucl.ac.be/artifactory/libs-snapshot-local/"
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.12.4"

