package io.github.mvillafuertem

import io.github.mvillafuertem.hooks.{ useCounter, useFetch }
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._

@react object MultipleCustomHooksApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (counter, increment, _, _) = useCounter(1)
    val (data, loading)            = useFetch(s"https://www.breakingbadapi.com/api/quotes/${counter}")

    div(
      h1("BrakingBad Quotes"),
      if (loading)
        div(className := "alert alert-info text-center")("Loading...")
      else
        blockquote(className := "blockquote text-right")(
          p(className := "mb-0")(data.quote),
          footer(className := "blockquote-footer")(data.author)
        ),
      button(className := "btn btn-primary", onClick := increment)("Next quote")
    )

  }

}
