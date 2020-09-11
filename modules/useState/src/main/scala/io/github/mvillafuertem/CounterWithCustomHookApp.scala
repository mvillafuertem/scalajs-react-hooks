package io.github.mvillafuertem

import io.github.mvillafuertem.hooks.useCounter
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.web.html._

@react object CounterWithCustomHookApp {

  case class Props(value: Int = 0)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { case Props(value) =>
    val (counter, increment, decrement, reset) = useCounter(value)

    Fragment(
      h1("CounterWithCustomHookApp"),
      h2(counter),
      button(className := "btn btn-success", onClick := increment)("+1"),
      button(className := "btn btn-primary", onClick := reset)("Reset"),
      button(className := "btn btn-danger", onClick := decrement)("-1")
    )

  }

}
