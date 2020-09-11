package io.github.mvillafuertem

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.{useCallback, useState}
import slinky.web.html.{h1, p}

@react object Father {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val numbers           = (1 to 10).filter(_ % 2 == 0)
    val (value, setValue) = useState[Int](0)

    val incrementCallback =
      useCallback((n: Int) => setValue(counter => counter + n), Seq(setValue))

    Fragment(
      h1("Father"),
      p(s"Total ${value}"),
      numbers.map(n => Child(n, incrementCallback).withKey(n.toString))
    )

  }

}
