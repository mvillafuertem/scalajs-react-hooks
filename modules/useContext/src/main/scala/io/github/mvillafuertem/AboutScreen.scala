package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{ Fragment, Hooks }
import slinky.web.html.{ button, className, h1, hr, onClick, pre }

import scala.scalajs.js

@react object AboutScreen {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (user, setUser) = Hooks.useContext(userContext)

    Fragment(
      h1("AboutScreen"),
      hr(),
      pre(js.JSON.stringify(user, space = 3)),
      button(className := "btn btn-warning", onClick := (() => setUser(initialUser)))("Logout")
    )
  }

}
