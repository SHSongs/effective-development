import LIST._

import java.util.UUID

case class User(id: UUID, name: String, age: Int)

object User {
  val makeUsers = CONS(
    User(UUID.randomUUID(), "abc", 20),
    (CONS(
      User(UUID.randomUUID(), "wasd", 30),
      CONS(
        User(UUID.randomUUID(), "zxc", 40),
        NIL())
    ))
  )

}
