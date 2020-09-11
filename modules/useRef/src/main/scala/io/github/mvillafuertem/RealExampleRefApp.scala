package io.github.mvillafuertem

import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.useState
import slinky.core.{FunctionalComponent, TagElement}
import slinky.web.SyntheticMouseEvent
import slinky.web.html._

@react object RealExampleRefApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (show, setShow) = useState(false)

    val handleClick: SyntheticMouseEvent[TagElement#RefType] => Unit =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setShow(!show)

    Fragment(
      h1("RealExampleRefApp"),
      if (show) {
        MultipleCustomHooksApp()
      } else p(),
      button(className := "btn btn-outline-primary", onClick := handleClick)("Show/Hide Quotes")
    )

  }

}
