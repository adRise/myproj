import sbt._
import sbt.Keys._

import bintray.BintrayPlugin.autoImport.bintray

object Common {

  private final val Version = "1.2.2"

  lazy val settings =
    Seq(
      version       := Version,
      organization  := "com.example",
      javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),

      publishTo in bintray := {
        val ts = System.currentTimeMillis()
        val root = "https://tubins.jfrog.io/tubins"
        if (version.value.trim.endsWith("SNAPSHOT"))
          Some("sbt-dev" at s"$root/sbt-dev;build.timestamp=$ts")
        else
          //Some("sbt-release" at s"$root/sbt-release")
          Some("sbt-dev" at s"$root/sbt-dev")
      },

      resolvers in bintray ++= Seq(
        "sbt-release" at "https://tubins.jfrog.io/tubins/sbt-release/",
        "sbt-dev"     at "https://tubins.jfrog.io/tubins/sbt-dev/"
      )
    )
}
