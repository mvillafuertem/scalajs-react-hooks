package io.github.mvillafuertem

import org.scalajs.dom.document
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useState
import slinky.core.facade.{Fragment, Hooks, SetStateHookCallback}
import slinky.web.ReactDOM
import slinky.web.html.{h1, hr}
import zio.{App, ExitCode, IO, ZIO}

object UseContextApp extends App {

  @react object Main {

    type Props = Unit

    val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
      val (user: User, setUser: SetStateHookCallback[User]) = useState(initialUser)
      userContext.Provider((user, setUser))(
        AppRouter()
      )
    }
  }

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    IO.effectTotal {
      ReactDOM
        .render(
          Main(),
          document.getElementById("container")
        )
    }.exitCode

}
