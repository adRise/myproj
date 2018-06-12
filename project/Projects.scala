import sbt._
import sbt.Keys._

import ch.epfl.scala.sbt.release.AutoImported._
import com.typesafe.sbt.pgp.PgpKeys._

object Projects {

  lazy val root =
    (project in file("."))
      .settings(
        name               := "myproj",

        Common.settings,

        // Prevent the `publish*` commands from creating artifacts for the root project
        publish      := (()),
        publishLocal := (()),
        publishM2    := (()),

        // Global release settings
        licenses   in Global := Seq("MIT" -> url("https://opensource.org/licenses/mit")),
        homepage   in Global := Some(url("https://github.com/some-homepage-example-dev/myproj")),
        developers in Global := List(Developer("developerid", "Software A. Developer", "developer@example.com", url("https://github.com/some-homepage-example-dev/myproj-website"))),
        scmInfo    in Global := Some(ScmInfo(url("https://github.com/some-homepage-example-dev/myproj-scm"), "scm:git:git@github.com:some-homepage-example-dev/myproj-scm")),

        // Global `sbt-release-early` settings
        //pgpPublicRing    in Global := file("./keys/pubring.asc"),
        //pgpSecretRing    in Global := file("./keys/secring.asc"),
        releaseEarlyWith in Global := BintrayPublisher,

        // Enable releases from your local machine (for testing)
        releaseEarlyInsideCI            in Global := true,
        releaseEarlyEnableLocalReleases in Global := true
      )
      .aggregate(
        myprojCompiler,
        myprojRuntime
      )

  lazy val myprojCompiler =
    Project(id = "myproj-compiler", base = file("myproj-compiler"))
      .settings(
        name               := "myproj-compiler",

        Common.settings,

        sbtPlugin    := true,
        sbtVersion   := "1.1.6",
        scalaVersion := "2.12.6",

        libraryDependencies += Libraries.scalapbCompiler
      )

  lazy val myprojRuntime =
    Project(id = "myproj-runtime", base = file("myproj-runtime"))
      .settings(
        name               := "myproj-runtime",

        Common.settings,
        scalaVersion := "2.12.6",

        libraryDependencies ++= Seq(
          Libraries.akkaStreams,
          Libraries.grpcStub,
          Libraries.reactiveStreams
        )
      )
}
