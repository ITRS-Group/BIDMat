import sbt.Keys._
import sbt._
import java.io.File


object ValoNativeBuild extends Build {

  val bidmatVersion = "1.0.4"

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
    version := bidmatVersion,

    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature", "-language:postfixOps", "-target:jvm-1.7"),
    javacOptions in(Compile, compile) ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.7", "-target", "1.8"),

    publishTo := Some("repo" at publishRepo(version.value)),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

  val os = ConfigurationSettings.os.getOrElse {
    System.getProperty("os.name") match {
      case name if name.startsWith("Windows") => "windows"
      case name if name.startsWith("OS X") => "osx"
      case _ => "linux"
    }
  }

  val prefix = os match {
      case "windows" => ""
      case "osx" => "lib"
      case _ => "lib"
    }

  val extension = os match {
    case "windows" => "dll"
    case "osx" => "dylib"
    case _ => "so"
  }

  val osToFileName = os match {
    case "osx" => "apple"
    case x => x
  }

  val arch = ConfigurationSettings.arch.getOrElse {
    System.getProperty("os.arch").toLowerCase match {
      case x if x == "i386" || x == "x86" || x == "i686" => "x86"
      case x if x.startsWith("amd64") || x.startsWith("x86_64") => "x86_64"
      case x if x == "ppc" || x == "powerpc" => "ppc"
      case x if x.startsWith("ppc") => "ppc_64"
      case x if x.startsWith("sparc") => "spark"
      case x if x.startsWith("arm") => "arm"
      case x if x.startsWith("mips") => "mips"
      case x if x.contains("risc") => "risc"
      case _ => "unknown"
    }
  }

  def resolve(root: String): (File, String) = {
    val fileName = s"$prefix$root-$osToFileName-$arch.$extension"
    println(fileName)
    new File(ConfigurationSettings.libDir + fileName) -> s"lib/$fileName"
  }

  val iomp5 = {
    val iomp5Name =  s"libiomp5" + (os match {
      case "windows" => "md.dll"
      case "osx" => ".dylib"
      case _ => ".so"
    })
    new File(ConfigurationSettings.libDir + iomp5Name) -> s"lib/$iomp5Name"
  }

  def libFile(info: (File, String)) = Seq(
    mappings in(Compile, packageBin) += info
  )

  object Publish {
    lazy val none = Seq(
      publish :=(),
      publishLocal :=()
    )
  }

  lazy val root = Project("root", file("."),
    settings = baseSettings ++ Publish.none
  ).aggregate(binaries)

  lazy val binaries = Project(s"valo-bidmat-natives-$os", file(s"valo-bidmat-natives-$os"),
    settings = baseSettings ++
      libFile(resolve("bidmatcpu")) ++
      libFile(resolve("bidmatcuda")) ++
      libFile(iomp5)
  )
}