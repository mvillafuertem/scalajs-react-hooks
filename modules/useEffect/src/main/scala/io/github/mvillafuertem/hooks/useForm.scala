package io.github.mvillafuertem.hooks

import io.github.mvillafuertem.model.Person
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLInputElement
import slinky.core.facade.Hooks.useState
import slinky.core.{ SyntheticEvent, TagElement }

import scala.scalajs.js

object useForm {

  def apply(initialState: Person) = {
    val (state, setState) = useState(initialState)

    val handleInputChange: js.Function1[SyntheticEvent[TagElement#RefType, Event], Unit] =
      (e: SyntheticEvent[TagElement#RefType, Event]) =>
        e.target.asInstanceOf[HTMLInputElement].name match {
          case "email"    => setState(Person(state.name, email = e.target.asInstanceOf[HTMLInputElement].value, state.password))
          case "name"     => setState(Person(name = e.target.asInstanceOf[HTMLInputElement].value, state.email, state.password))
          case "password" => setState(Person(state.name, state.email, password = e.target.asInstanceOf[HTMLInputElement].value))
        }

    (state, handleInputChange)
  }

}
