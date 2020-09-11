package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.{useCallback, useState}
import slinky.web.html.{h1, h3}

@react object CallbackHookApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (counter, setCounter) = useState[Int](0)


    val incrementCallback: () => Unit =
      useCallback(() => setCounter(counter => counter + 1), Seq(setCounter))

    Fragment(
      h1("CallbackHookApp"),
      h3(counter),
      ShowIncrement(incrementCallback)
    )
  }

}
