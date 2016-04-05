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
  val scala_parser_combinators = "org.scala-lang" % "scala-parser-combinators" % "2.11.0-M4"
  val scalaList = Seq(scala_reflect, scala_compiler, scala_library)//, scala_parser_combinators)

  val lz4 = "net.jpountz.lz4" % "lz4" % "1.3"
  val jline = "jline" % "jline" % "2.10"

  val jcuda = "com.itrsgroup" % "valo-jcuda" % "0.7.5"
  val jcuda_windows = "com.itrsgroup" % "valo-jcuda-windows" % "0.7.5"
  val jcublas = "com.itrsgroup" % "valo-jcublas" % "0.7.5"
  val jcublas_windows = "com.itrsgroup" % "valo-jcublas-windows" % "0.7.5"
  val jcudavec = "com.itrsgroup" % "valo-jcudavec" % "0.7.5"
  val jcufft = "com.itrsgroup" % "valo-jcufft" % "0.7.5"
  val jcufft_windows = "com.itrsgroup" % "valo-jcufft-windows" % "0.7.5"
  val jcurand = "com.itrsgroup" % "valo-jcurand" % "0.7.5"
  val jcurand_windows = "com.itrsgroup" % "valo-jcurand-windows" % "0.7.5"
  val jcusolver = "com.itrsgroup" % "valo-jcusolver" % "0.7.5"
  val jcusolver_windows = "com.itrsgroup" % "valo-jcusolver-windows" % "0.7.5"
  val jcusparse = "com.itrsgroup" % "valo-jcusparse" % "0.7.5"
  val jcusparse_windows = "com.itrsgroup" % "valo-jcusparse-windows" % "0.7.5"

  val jcudaList = Seq(
    jcuda, jcuda_windows,
    jcublas, jcublas_windows,
    jcudavec,
    jcufft, jcufft_windows,
    jcurand, jcurand_windows,
    jcusolver, jcusolver_windows,
    jcusparse, jcusparse_windows)

  val commons_math = "org.apache.commons" % "commons-math3" % "3.2"

  val hdf = "org.scala-saddle" % "jhdf5" % "2.9"

  val scala_test = "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
  val junit = "junit" % "junit" % "4.11" % "test"
  val scala_check = "org.scalacheck" %% "scalacheck" % "1.11.6" % "test"
  val testList = Seq(scala_test, junit, scala_check)

  val bidmat_dependencies = Seq(lz4, jline, commons_math, hdf) ++ jcudaList ++ scalaList ++ testList
}