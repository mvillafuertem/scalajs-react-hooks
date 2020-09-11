package io.github.mvillafuertem

import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.useState
import slinky.core.{ FunctionalComponent, TagElement }
import slinky.web.SyntheticMouseEvent
import slinky.web.html._

@react object CounterApp {

  case class Props(value: Int = 0)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { case Props(value) =>
    val (counter, setCounter) = useState(value)

    val handleAdd: SyntheticMouseEvent[TagElement#RefType] => Unit =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setCounter(counter + 1)

    val handleReset: SyntheticMouseEvent[TagElement#RefType] => Unit =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setCounter(value)

    val handleSubtract: SyntheticMouseEvent[TagElement#RefType] => Unit =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setCounter(counter - 1)

    Fragment(
      h1("CounterApp"),
      h2(counter),
      button(className := "btn btn-success", onClick := handleAdd)("+1"),
      button(className := "btn btn-primary", onClick := handleReset)("Reset"),
      button(className := "btn btn-danger", onClick := handleSubtract)("-1")
    )

  }

}
