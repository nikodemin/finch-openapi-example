package com.github.nikodemin.finchopenapiexample

import cats.effect.{ExitCode, IO, IOApp, Resource}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Http, Service}
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

object Main extends IOApp {

  case class Message(hello: String)

  def healthcheck: Endpoint[IO, String] = get(pathEmpty) {
    Ok("OK")
  }

  def helloWorld: Endpoint[IO, Message] = get("hello") {
    Ok(Message("World"))
  }

  def hello: Endpoint[IO, Message] = get("hello" :: path[String]) { s: String =>
    Ok(Message(s))
  }

  def service: Resource[IO, Service[Request, Response]] =
    Bootstrap[IO]
      .serve[Text.Plain](healthcheck)
      .serve[Application.Json](helloWorld :+: hello)
      .toService

  override def run(args: List[String]): IO[ExitCode] =
    service.map(Http.server.serve(":8081", _)).useForever.as(ExitCode.Success)
}
