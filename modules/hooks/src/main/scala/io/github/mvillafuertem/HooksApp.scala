package io.github.mvillafuertem

import org.scalajs.dom.document
import slinky.core.facade.Fragment
import slinky.web.ReactDOM
import slinky.web.html._
import zio.{ App, ExitCode, IO, ZIO }

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object HooksApp extends App {

  @JSImport("./App.css", JSImport.Default)
  @js.native
  object AppCSS extends js.Object

  private val css = AppCSS

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    IO.effectTotal(
      ReactDOM
        .render(
          Fragment(
            CounterApp(),
            hr(),
            CounterWithCustomHookApp(),
            hr(),
            FormApp(),
            hr(),
            FormWithCustomHookApp(),
            hr(),
            MultipleCustomHooksApp(),
            hr(),
            FocusScreenApp(),
            hr(),
            RealExampleRefApp(),
            hr(),
            LayoutEffectApp(),
            hr(),
            MemorizeApp(),
            hr(),
            MemoHookApp(),
            hr(),
            CallbackHookApp(),
            hr(),
            Father(),
            hr(),
            TodoApp()
          ),
          document.getElementById("container")
        )
    ).exitCode

}
