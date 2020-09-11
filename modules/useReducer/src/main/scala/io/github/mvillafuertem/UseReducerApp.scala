package io.github.mvillafuertem

import io.github.mvillafuertem.css.AppCSS
import org.scalajs.dom.document
import slinky.core.facade.Fragment
import slinky.web.ReactDOM
import slinky.web.html.hr
import zio.{ App, ExitCode, IO, ZIO }

object UseReducerApp extends App {

  private val css = AppCSS

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    IO.effectTotal(
      ReactDOM
        .render(
          Fragment(
            hr(),
            TodoApp(),
            hr()
          ),
          document.getElementById("container")
        )
    ).exitCode

}
