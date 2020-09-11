package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.React
import slinky.web.html.small

@react object Small {

  case class Props(value: Int)

  val component: FunctionalComponent[Props] = React.memo(
    FunctionalComponent[Props] {
      case Props(value) =>
        println("Refresh Component, only show when the props changes")
        small(value)
    },
    (oldProps: Props, newProps: Props) => oldProps.value == newProps.value
  )

}
