package io.github.mvillafuertem.model

import scala.scalajs.js

class Quote(val quote_id: String, val quote: String, val author: String, val series: String) extends js.Object

object Quote {

  val default: Quote = apply()

  def apply(quote_id: String = "", quote: String = "", author: String = "", series: String = ""): Quote =
    new Quote(quote_id, quote, author, series)

}

//@js.native
//trait Quote extends js.Object {
//  val quote_id: String = js.native
//  val quote: String = js.native
//  val author: String = js.native
//  val series: String = js.native
//}
