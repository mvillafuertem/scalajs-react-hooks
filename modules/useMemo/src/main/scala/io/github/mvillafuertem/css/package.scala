package io.github.mvillafuertem

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

package object css {

  @JSImport("./App.css", JSImport.Default)
  @js.native
  object AppCSS extends js.Object

}
