package io.github.mvillafuertem

import org.scalajs.dom.document
import org.scalajs.dom.raw.{ ClientRect, HTMLParagraphElement }
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks.{ useLayoutEffect, useRef, useState }
import slinky.web.html.{ h1, p, _ }
import io.github.mvillafuertem.hooks.useCounter
import io.github.mvillafuertem.hooks.useFetch

import scala.scalajs.js

@react object LayoutEffectApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (counter, increment, _, _) = useCounter(1)
    val (data, loading)            = useFetch(s"https://www.breakingbadapi.com/api/quotes/${counter}")
    val refTag                     = useRef[HTMLParagraphElement](null)
    val (boxSize, setBoxSize)      = useState[ClientRect](document.createElement("p").getBoundingClientRect())

    useLayoutEffect(() => setBoxSize(refTag.current.getBoundingClientRect()), Seq(data.quote))

    div(
      h1("LayoutEffectApp"),
      blockquote(className := "my-blockquote blockquote text-right")(
        p(id := "my-p", className := "mb-0", ref := refTag)(data.quote)
      ),
      pre(js.JSON.stringify(boxSize, space = 3)),
      button(className := "btn btn-primary", onClick := increment)("Next quote")
    )

  }

}
