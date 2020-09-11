package io.github.mvillafuertem

import io.github.mvillafuertem.css.AppCSS
import org.scalajs.dom.document
import slinky.core.facade.Fragment
import slinky.web.ReactDOM
import slinky.web.html._
import zio.{ App, ExitCode, IO, ZIO }

object UseRefApp extends App {

  private val css = AppCSS

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    IO.effectTotal(
      ReactDOM
        .render(
          Fragment(
            hr(),
            MultipleCustomHooksApp(),
            hr(),
            FocusScreenApp(),
            hr(),
            RealExampleRefApp(),
            hr()
          ),
          document.getElementById("container")
        )
    ).exitCode

}
