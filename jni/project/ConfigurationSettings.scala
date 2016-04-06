
import sbt.Keys._
import sbt._

object ConfigurationSettings {
  val os: Option[String] = None // windows, osx or linux
  val arch: Option[String] = None
  val libDir: String = "./lib/"
}
