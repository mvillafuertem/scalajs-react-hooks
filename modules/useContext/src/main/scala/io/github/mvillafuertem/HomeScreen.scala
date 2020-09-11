package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{ Fragment, Hooks, SetStateHookCallback }
import slinky.web.html.{ h1, hr, pre }

import scala.scalajs.js

@react object HomeScreen {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (user, _) = Hooks.useContext(userContext)
    Fragment(
      h1(s"HomeScreen"),
      hr(),
      pre(js.JSON.stringify(user, space = 3))
    )
  }

}
