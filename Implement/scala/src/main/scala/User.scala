import LIST._

import java.util.UUID

case class User(id: UUID = UUID.randomUUID(), name: String, age: Int)

object User {
  implicit val Ord: Ordering[User] = Ordering.by(_.name)
  
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
