package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.{ className, div }
import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.components.{ Redirect, Route, Switch, BrowserRouter => Router }

@react object AppRouter {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    Router(
      div(
        NavBar(),
        div(className := "container")(
          Switch(
            Route(RouteProps().setExact(true).setPath("/"))(HomeScreen()),
            Route(RouteProps().setExact(true).setPath("/about"))(AboutScreen()),
            Route(RouteProps().setExact(true).setPath("/login"))(LoginScreen()),
            Redirect("/")
          )
        )
      )
    )
  }

}
