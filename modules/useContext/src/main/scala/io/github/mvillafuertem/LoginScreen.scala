package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{ Fragment, Hooks }
import slinky.web.html._

@react object LoginScreen {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (_, setUser) = Hooks.useContext(userContext)
    Fragment(
      h1("LoginScreen"),
      hr(),
      button(className := "btn btn-primary", onClick := (() => setUser(defaultUser)))("Login")
    )
  }

}
