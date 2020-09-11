package io.github.mvillafuertem

import org.scalajs.dom.raw.HTMLInputElement
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.useRef
import slinky.core.{ FunctionalComponent, TagElement }
import slinky.web.SyntheticMouseEvent
import slinky.web.html._

@react object FocusScreenApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val inputRef = useRef[HTMLInputElement](null)

    val handleClick: SyntheticMouseEvent[TagElement#RefType] => Unit =
      (_: SyntheticMouseEvent[TagElement#RefType]) => inputRef.current.select()
// Example using dom ref without hook useRef
//    document
//      .getElementById("my-input")
//      .asInstanceOf[HTMLInputElement]
//      .select()

    Fragment(
      h1("FocusScreenApp"),
      div(className := "form-group")(
        input(
          `type` := "text",
          name := "name",
          ref := inputRef,
          className := "form-control",
          placeholder := "Name",
          autoComplete := "off"
          //value := pname,
          //onChange := handleInputChange
        )
      ),
      button(className := "btn btn-outline-primary", onClick := handleClick)("Focus")
    )

  }

}
