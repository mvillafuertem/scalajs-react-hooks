package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._
import typings.reactRouterDom.components.{ Link, NavLink, BrowserRouter => Router }

@react object NavBar {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    nav(className := "navbar navbar-expand-sm navbar-dark bg-dark")(
      Link[String](to = "/").className("navbar-brand")("useContextApp"),
      div(className := "collapse navbar-collapse", id := "navbarNavAltMarkup")(
        div(className := "navbar-nav")(
          NavLink[String](to = "/")
            .exact(true)
            .activeClassName("active")
            .className("nav-item nav-link")("Home"),
          NavLink[String](to = "/about")
            .exact(true)
            .activeClassName("active")
            .className("nav-item nav-link")("About"),
          NavLink[String](to = "/login")
            .exact(true)
            .activeClassName("active")
            .className("nav-item nav-link")("Login")
        )
      )
    )
  }

}
