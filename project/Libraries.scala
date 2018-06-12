import sbt._
import sbt.Keys._

import scalapb.compiler.Version.grpcJavaVersion
import scalapb.compiler.Version.scalapbVersion

object Libraries {
  val akkaStreams     = "com.typesafe.akka"      %% "akka-stream"      % "2.5.9"
  val grpcStub        = "io.grpc"                 % "grpc-stub"        % grpcJavaVersion
  val reactiveStreams = "org.reactivestreams"     % "reactive-streams" % "1.0.1"
  val scalapbCompiler = "com.thesamet.scalapb"   %% "compilerplugin"   % scalapbVersion
}
