package io.github

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

package object mvillafuertem {

  // Simulate a call to API HTTP
  def apiHttp: js.Function1[Int, String] = { (x: Int) =>
    for (_ <- 1 to x) yield println("Loading...")
    s"elements $x"
  }

}
