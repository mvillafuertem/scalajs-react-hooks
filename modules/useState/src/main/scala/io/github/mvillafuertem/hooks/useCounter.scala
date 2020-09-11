package io.github.mvillafuertem.hooks

import slinky.core.TagElement
import slinky.core.facade.Hooks.useState
import slinky.web.SyntheticMouseEvent

import scala.scalajs.js

object useCounter {

  def apply(initialState: Int) = {
    val (state, setState) = useState(initialState)

    val increment: js.Function1[SyntheticMouseEvent[TagElement#RefType], Unit] =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setState(state + 1)

    val decrement: js.Function1[SyntheticMouseEvent[TagElement#RefType], Unit] =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setState(state - 1)

    val reset: js.Function1[SyntheticMouseEvent[TagElement#RefType], Unit] =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setState(initialState)

    (state, increment, decrement, reset)
  }

}
