package io.github.mvillafuertem

import org.scalajs.dom.{Event, window}
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useEffect
import slinky.web.html.{div, h3}

import scala.scalajs.js

@react object MessageApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    useEffect(
      () => {

        //val eventToUnit: Event => Props = (e: Event) => println((e.asInstanceOf[raw.MouseEvent].clientX, e.asInstanceOf[raw.MouseEvent].clientY))
        // Must cast to js.Function to make sure that the conversion from scala Function to js function
        val eventToUnit: js.Function1[Event, Unit] = (e: Event) => println(":D")

        window.addEventListener("mousemove", eventToUnit)

        () => window.removeEventListener("mousemove", eventToUnit)
      },
      Seq.empty
    )
    div(
      h3("You are awesome")
    )

  }

}
