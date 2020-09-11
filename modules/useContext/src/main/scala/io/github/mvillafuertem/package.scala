package io.github

import java.util.UUID

import slinky.core.facade.{ React, ReactContext, SetStateHookCallback }

import scala.scalajs.js

package object mvillafuertem {

  class User(val name: String, val id: String = UUID.randomUUID().toString) extends js.Object

  val initialUser = new User("", "")
  def defaultUser = new User("Pepe")

  val userContext: ReactContext[(User, SetStateHookCallback[User])] =
    React.createContext[(User, SetStateHookCallback[User])]((initialUser, new SetStateHookCallback[User](_ => ())))

}
