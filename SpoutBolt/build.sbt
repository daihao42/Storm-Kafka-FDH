name := "GongAnStrom"

version := "1.0"

scalaVersion := "2.11.8"

//resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/repo/"
resolvers ++= Seq("clojars" at "http://clojars.org/repo/",
                  "clojure-releases" at "http://build.clojure.org/releases",
				  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
				  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/repo/")


libraryDependencies ++= Seq(
  //"org.apache.storm" % "storm-core" % "1.0.2" % "provided",
  "org.apache.storm" % "storm-core" % "1.0.2",
  "org.apache.storm" % "storm-kafka" % "1.0.2",
  //"org.apache.kafka" % "kafka-clients" % "0.9.0.1",
  "org.apache.kafka" % "kafka-clients" % "0.8.2.2",
  "org.apache.kafka" %% "kafka" % "0.8.2.2",
)


assemblyMergeStrategy in assembly := {

case PathList("javax", "servlet", xs@_*) => MergeStrategy.last

case PathList("javax", "activation", xs@_*) => MergeStrategy.last

// case PathList("org", "apache", xs@_*) => MergeStrategy.last

case PathList("org", "w3c", xs@_*) => MergeStrategy.last

case PathList("com", "google", xs@_*) => MergeStrategy.last

case PathList("com", "codahale", xs@_*) => MergeStrategy.last

case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first

case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first

case x =>

val oldStrategy = (assemblyMergeStrategy in assembly).value

oldStrategy(x)

}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.google.common.**" -> "shadeio.@1").inAll
)
