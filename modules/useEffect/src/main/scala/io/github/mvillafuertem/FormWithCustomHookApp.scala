package io.github.mvillafuertem

import io.github.mvillafuertem.hooks.useForm
import io.github.mvillafuertem.model.Person
import org.scalajs.dom.Event
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useEffect
import slinky.core.{FunctionalComponent, SyntheticEvent, TagElement}
import slinky.web.html._

@react object FormWithCustomHookApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    val (formState, handleInputChange) = useForm(Person.default)


    useEffect(() => println("changed Email"), Seq(formState.email))

    val handleSubmit: SyntheticEvent[TagElement#RefType, Event] => Unit =
      (e: SyntheticEvent[TagElement#RefType, Event]) => {
        e.preventDefault()
        println(formState)
      }

    form(onSubmit:=handleSubmit)(
      h1("FormWithCustomHookApp"),
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
      div(className := "form-group")(
        input(
          `type` := "password",
          name := "password",
          className := "form-control",
          placeholder := "Password",
          value := formState.password,
          onChange := handleInputChange
        )
      ),
      button(`type`:="submit", className:="btn btn-primary")("Save")
    )

  }

}

