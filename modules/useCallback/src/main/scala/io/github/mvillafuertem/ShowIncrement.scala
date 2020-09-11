package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.React
import slinky.web.html.{button, className, onClick}

@react object ShowIncrement {

  case class Props(increment: () => Unit)

  val component: FunctionalComponent[Props] = React.memo(
    FunctionalComponent[Props] {
      case Props(increment) =>
        println("Refresh Component, only show when the props changes")
        button(className := "btn btn-success", onClick := increment)("+1")

    },
    (oldProps: Props, newProps: Props) => oldProps.increment eq newProps.increment
  )

}
