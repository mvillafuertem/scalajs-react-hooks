package io.github.mvillafuertem.hooks

import org.scalajs.dom.experimental.{ Fetch, Response }
import slinky.core.facade.Hooks.{ useEffect, useRef, useState }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.scalajs.js

//@js.native
//trait Quote extends js.Object {
//  val quote_id: String = js.native
//  val quote: String = js.native
//  val author: String = js.native
//  val series: String = js.native
//}

object useFetch {

  class Quote(val quote_id: String, val quote: String, val author: String, val series: String) extends js.Object
  //type RVAL[A] = A | js.Thenable[A]
  //type RESOLVE[A, B] = js.Function1[A, RVAL[B]]
  //type REJECTED[A] = js.Function1[scala.Any, RVAL[A]]

  def apply(url: String) = {

    val (state, setState) = useState((new Quote("", "", "", ""), true))

    val isMounted = useRef(true)

    useEffect(
      () =>
        () => {
          isMounted.current = false // CleanUp
        },
      Seq.empty
    )

    useEffect(
      () => {
        setState((new Quote("", "", "", ""), true))
        //val onf: RESOLVE[Response, Either[scala.Any, Response]] = (a: Response) => Right[scala.Any, Response](a)

        //val onr: REJECTED[Either[scala.Any, Response]] = (err: scala.Any) => Left[scala.Any, Response](err)

        //val value: Thenable[Either[Any, Response]] = Fetch.fetch(url).`then`[Either[scala.Any, Response]](onf, onr)
        scala.scalajs.js.timers.setTimeout(0.seconds) {
          if (isMounted.current)
            (for {
              //_                  <- Future.failed(new RuntimeException("Element Cancel"))
              //if !isMounted.current // This is necessary because it can produce memory leak,
              // when the element is not show
              // Warning: Can't perform a React state update on an unmounted component.
              // This is a no-op, but it indicates a memory leak in your application.
              // To fix, cancel all subscriptions and asynchronous tasks in a useEffect cleanup function.
              response: Response <- Fetch.fetch(url).toFuture
              text               <- response
                                      .json()
                                      .asInstanceOf[js.Promise[js.Array[Quote]]]
                                      .toFuture if response.status == 200
            } yield {
              println(js.JSON.stringify(text))
              setState((text(0), false))
            }).recover {
              case e: Exception => println(e.getMessage)
            }
          else println("no called setState")
        }
      },
      Seq(url)
    )

    state
  }

}
