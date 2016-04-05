import sbt.Keys._
import sbt._

//noinspection ScalaFileName
object TheBuild extends Build {

  object Publish {
    lazy val none = Seq(
      publish := (),
      publishLocal := ()
    )
  }

  def publishRepo(version: String) = {
    val packages = "https://itrsgroup.artifactoryonline.com/"
    if (version.trim.toUpperCase.contains("SNAPSHOT"))
      packages + "itrsgroup/libs-snapshot-local"
    else
      packages + "itrsgroup/libs-release-local"
  }

  val baseSettings = Seq(
    organization := "com.itrsgroup",
    crossPaths := false,
    scalaVersion := "2.11.5",
    version := "1.1.0",

    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature", "-language:postfixOps", "-target:jvm-1.7"),
    javacOptions in (Compile, compile) ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.8", "-target", "1.8"),

    publishTo := Some("repo" at publishRepo(version.value)),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

  lazy val BIDMatProject = Project("valo-bidmat", file("."),
    settings = baseSettings ++ Seq (
      libraryDependencies ++= Dependencies.bidmat_dependencies
    )
  )
}