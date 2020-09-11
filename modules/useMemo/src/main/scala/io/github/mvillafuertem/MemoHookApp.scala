package io.github.mvillafuertem

import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.{ useMemo, useState }
import slinky.core.{ FunctionalComponent, TagElement }
import slinky.web.SyntheticMouseEvent
import slinky.web.html._
import io.github.mvillafuertem.hooks.useCounter

import scala.scalajs.js

@react object MemoHookApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (counter, increment, _, _) = useCounter(1000)
    val (show, setShow)            = useState(true)

    val handleClick: js.Function1[SyntheticMouseEvent[TagElement#RefType], Unit] =
      (_: SyntheticMouseEvent[TagElement#RefType]) => setShow(!show)

    val memo = useMemo(() => apiHttp(counter), Seq(counter))

    Fragment(
      h1("MemoHookApp"),
      h3(counter),
      p(memo),
      button(className := "btn btn-success", onClick := increment)("+1"),
      button(className := "btn btn-outline-success ml-3", onClick := handleClick)(s"Show/Hide ${js.JSON.stringify(show)}")
    )

  }

}
