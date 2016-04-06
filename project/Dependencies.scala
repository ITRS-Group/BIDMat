import sbt.Keys._
import sbt._

object Dependencies {

  // Non standard remote repositories
  // -------------------------------------------------------------------------------------------------------------------
  val resolvers = Seq(
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    Keys.resolvers += "ITRS Internal Releases" at "http://itrsgroup.artifactoryonline.com/itrsgroup/libs-release-local",
    Keys.resolvers += "ITRS Internal Snapshots" at "http://itrsgroup.artifactoryonline.com/itrsgroup/libs-snapshot-local",
    Keys.resolvers += "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/",
    Keys.resolvers += "Scala Mirror" at "https://oss.sonatype.org/content/repositories/releases/"
  )

  val scala_reflect = "org.scala-lang" % "scala-reflect" % "2.11.7"
  val scala_compiler = "org.scala-lang" % "scala-compiler" % "2.11.7"
  val scala_library = "org.scala-lang" % "scala-library" % "2.11.7"
  val scalaList = Seq(scala_reflect, scala_compiler, scala_library)

  val lz4 = "net.jpountz.lz4" % "lz4" % "1.3"
  val jline = "jline" % "jline" % "2.10"

  val jcuda = "com.itrsgroup" % "valo-jcuda" % "0.7.5"
  val jcublas = "com.itrsgroup" % "valo-jcublas" % "0.7.5"
  val jcudavec = "com.itrsgroup" % "valo-jcudavec" % "0.7.5"
  val jcufft = "com.itrsgroup" % "valo-jcufft" % "0.7.5"
  val jcurand = "com.itrsgroup" % "valo-jcurand" % "0.7.5"
  val jcusolver = "com.itrsgroup" % "valo-jcusolver" % "0.7.5"
  val jcusparse = "com.itrsgroup" % "valo-jcusparse" % "0.7.5"
  val jcudaNatives = Seq(
    "com.itrsgroup" % "valo-jcuda-natives-linux" % "0.7.5",
    "com.itrsgroup" % "valo-jcuda-natives-windows" % "0.7.5",
    "com.itrsgroup" % "valo-jcuda-natives-osx" % "0.7.5"
    )

  val jcudaList = jcudaNatives ++ Seq(jcuda, jcublas, jcudavec, jcufft, jcurand, jcusolver, jcusparse)

  val commons_math = "org.apache.commons" % "commons-math3" % "3.2"

  val hdf = "org.scala-saddle" % "jhdf5" % "2.9"

  val scala_test = "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
  val junit = "junit" % "junit" % "4.11" % "test"
  val scala_check = "org.scalacheck" %% "scalacheck" % "1.11.6" % "test"
  val testList = Seq(scala_test, junit, scala_check)

  val bidmat_natives = Seq (
    "com.itrsgroup" % "valo-bidmat-natives-windows" % "1.0.4",
    "com.itrsgroup" % "valo-bidmat-natives-linux" % "1.0.4",
    "com.itrsgroup" % "valo-bidmat-natives-osx" % "1.0.4"
  )

  val bidmat_dependencies = bidmat_natives ++ Seq(lz4, jline, commons_math, hdf) ++ jcudaList ++ scalaList ++ testList
}