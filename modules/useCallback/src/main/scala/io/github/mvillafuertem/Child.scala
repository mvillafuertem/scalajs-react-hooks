package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.React
import slinky.web.html.{button, className, onClick}

@react object Child {

  case class Props(number: Int, increment: Int => Unit)

  val component: FunctionalComponent[Props] = React.memo(
    FunctionalComponent[Props] {
      case Props(number, increment) =>
        println("Refresh Component, only show when the props changes")
        button(className := "btn btn-success ml-3", onClick := (() => increment(number)))(s"+${number}")

    }
  )

}
