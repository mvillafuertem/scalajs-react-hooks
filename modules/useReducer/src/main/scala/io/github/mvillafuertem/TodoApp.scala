package io.github.mvillafuertem

import java.time.Instant
import java.util.UUID

import io.github.mvillafuertem.hooks.useForm
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLInputElement
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks.{ useEffect, useReducer, useState }
import slinky.core.{ FunctionalComponent, SyntheticEvent, TagElement }
import slinky.web.SyntheticMouseEvent
import slinky.web.html.{ button, onSubmit, _ }
import slinky.web.html.onClick

import scala.scalajs.js

class Task(val description: String = "", val done: Boolean = false, val instant: String = Instant.now().toString, val id: String = UUID.randomUUID().toString)
    extends js.Object

sealed trait TaskAction
case class Add(task: Task)    extends TaskAction
case class Delete(id: String) extends TaskAction
case class Toggle(id: String) extends TaskAction

object TodoReducer {

  def apply(state: Seq[Task], action: TaskAction): Seq[Task] =
    action match {
      case Add(task)  => state :+ task
      case Delete(id) => state.filterNot(_.id.equals(id))
      case Toggle(id) =>
        state.map { task =>
          if (task.id.equals(id))
            new Task(task.description, !task.done, task.instant, task.id)
          else
            task
        }
      case _          => state
    }
}

@react object TodoApp {

  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>
    import js.JSConverters._
    val dynamic                       = js.JSON.parse(dom.window.localStorage.getItem("todos"))
    //val init                          = (_: Seq[Task]) => Seq.from(dynamic.asInstanceOf[js.Array[Task]])
    val (todos, dispatch)             = useReducer[Seq[Task], TaskAction](TodoReducer.apply, Seq.empty[Task])
    val (description, setDescription) = useState[String]("")
    //useEffect[Unit](() => dom.window.localStorage.setItem("todos", js.JSON.stringify(todos.toJSArray)), Seq(todos))

    val handleInputChange: js.Function1[SyntheticEvent[TagElement#RefType, Event], Unit] =
      (e: SyntheticEvent[TagElement#RefType, Event]) =>
        e.target.asInstanceOf[HTMLInputElement].name match {
          case "description" => setDescription(e.target.asInstanceOf[HTMLInputElement].value)
        }

    val handleDelete: String => Unit =
      (id: String) => dispatch(Delete(id))

    val handleToggle: String => Unit =
      (id: String) => dispatch(Toggle(id))

    val handleSubmit: SyntheticEvent[TagElement#RefType, Event] => Unit =
      (e: SyntheticEvent[TagElement#RefType, Event]) => {
        e.preventDefault()
        if (description.trim.nonEmpty) {
          dispatch(Add(new Task(description)))
          setDescription("")
        }

      }

    Fragment(
      h1(s"TodoApp ( ${todos.size} )"),
      div(className := "row")(
        div(className := "col-7")(
          ul(className := "list-group list-group-flush")(
            todos.zipWithIndex.map { case (todo, index) =>
              li(key := todo.id, className := "list-group-item")(
                p(className := s"""task-description ${if (todo.done) "task-complete"}""", onClick := (() => handleToggle(todo.id)))(
                  s"${index + 1}. ${todo.description}"
                ),
                button(className := "btn btn-danger", onClick := (() => handleDelete(todo.id)))("Delete")
              )
            }
          )
        ),
        div(className := "col-5")(
          h4("Add Task"),
          form(onSubmit := handleSubmit)(
            input(
              `type` := "text",
              onChange := handleInputChange,
              className := "form-control",
              name := "description",
              placeholder := "Type your task...",
              value := description,
              autoComplete := "off"
            ),
            button(`type` := "submit", className := "btn btn-outline-primary mt-2 btn-block")("Add")
          )
        )
      )
    )

  }

}
