package io.github.mvillafuertem

import io.github.mvillafuertem.model.Person
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLInputElement
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.{ useEffect, useState }
import slinky.core.{ FunctionalComponent, SyntheticEvent, TagElement }
import slinky.web.html._

@react object FormApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (formState, setFormState) = useState(Person.default)

    useEffect(() => println("changed FormState"), Seq(formState))
    useEffect(() => println("changed Email"), Seq(formState.email))

    val handleInputChange: SyntheticEvent[TagElement#RefType, Event] => Unit =
      (e: SyntheticEvent[TagElement#RefType, Event]) =>
        e.target.asInstanceOf[HTMLInputElement].name match {
          case "email" => setFormState(Person(formState.name, email = e.target.asInstanceOf[HTMLInputElement].value, formState.password))
          case "name" => setFormState(Person(name = e.target.asInstanceOf[HTMLInputElement].value, formState.email, formState.password))
        }

    Fragment(
      h1("FormApp"),
      div(className := "form-group")(
        input(
          `type` := "text",
          name := "name",
          className := "form-control",
          placeholder := "Name",
          autoComplete := "off",
          value := formState.name,
          onChange := handleInputChange
        )
      ),
      div(className := "form-group")(
        input(
          `type` := "text",
          name := "email",
          className := "form-control",
          placeholder := "Email",
          autoComplete := "off",
          value := formState.email,
          onChange := handleInputChange
        )
      ),
      if (formState.name.equals("123")) MessageApp() else null
    )

  }

}
