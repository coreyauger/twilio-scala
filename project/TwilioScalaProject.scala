import sbt.Keys._
import sbt._

object TwilioScalaProject extends Build with BuildExtra{
  import Resolvers._
  lazy val root = Project("twilio-scala", file(".")) settings(coreSettings : _*)

  lazy val commonSettings: Seq[Setting[_]] = Seq(
    organization := "systems.fail-fast",
    version := "0.4-SNAPSHOT",
    scalaVersion := "2.11.1",
    scalacOptions := Seq("-deprecation", "-unchecked", "-feature", "-language:postfixOps"),
    resolvers ++= Seq(akkaRelease, akkaSnapshot, sprayJson, sonatypeRelease, sonatypeSnapshot)
  )

  lazy val resolverSettings = Seq(
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    resolvers += "NextWave Repo" at "https://repository.conversant.im/artifactory/nxtwv-maven/",
    publishTo := Some("NextWave Repo" at "https://repository.conversant.im/artifactory/nxtwv-maven/")
  )

  val sprayV = "1.3.1"

  val akkaV = "2.3.9"

  lazy val coreSettings = commonSettings ++ resolverSettings ++ Seq(
    name := "twilio-scala",
    libraryDependencies :=
      Seq(
        "ch.qos.logback"      % "logback-classic"  % "1.0.13",
        "org.parboiled"       %%  "parboiled"     % "2.0.1",
        "io.spray"            %% "spray-can"        % sprayV,
        "io.spray"            %% "spray-http"    % sprayV,
        "io.spray"            %% "spray-client"    % sprayV,
        "com.typesafe.akka"  %% "akka-actor"       % akkaV,
        "com.typesafe.akka"  %% "akka-slf4j"       % akkaV,
        "io.spray"            %% "spray-httpx"    % sprayV,
        "io.spray"            %% "spray-util"    % sprayV,
        "io.spray" %%  "spray-json" % "1.2.6",
        "com.typesafe"         %   "config"            % "1.0.0",
        "com.typesafe.scala-logging"        %% "scala-logging-slf4j" % "2.0.3",
        "org.pegdown"         % "pegdown" % "1.4.2",
        "commons-io" % "commons-io" % "2.4",
        "commons-codec" % "commons-codec" % "1.9",
        "org.apache.commons"   % "commons-lang3"          % "3.1",
        "org.scalautils" % "scalautils_2.10" % "2.0",
        "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
      ),

    parallelExecution in Test := false/*,

    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },

    credentials += Credentials(Path.userHome / ".sbt" / "sonatype.credentials"),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { repo => false },
    pomExtra := (
      <url>https://github.com/fail-fast/twilio-scala</url>
        <licenses>
          <license>
            <name>Apache 2.0 License</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:fail-fast/twilio-scala.git</url>
          <connection>scm:git:git@github.com:fail-fast/twilio-scala.git</connection>
        </scm>
        <developers>
          <developer>
            <id>fail-fast</id>
            <name>Fail fast</name>
            <url>http://fail-fast.systems</url>
          </developer>
        </developers>),
    unmanagedResources in Compile <+= baseDirectory map { _ / "LICENSE" }*/

  )
}


object Resolvers {
  val akkaRelease = "typesafe release repo" at "http://repo.typesafe.com/typesafe/releases/"
  val akkaSnapshot = "typesafe snapshot repo" at "http://repo.typesafe.com/typesafe/snapshots/"
  val sprayJson = "spray" at "http://repo.spray.io/"
  val sonatypeSnapshot = "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  val sonatypeRelease = "Sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/"


}
