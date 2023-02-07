class test {

}
object Twice {
  def apply(x: Int): Int = x
  def unapply(z: Int): Option[Int] = if (z%2 == 0) Some(z/2) else None
}

object TwiceTest extends App {
  val x = Twice(21)
  x match {
    case Twice(n) => Console.println(n)
    case n => Console.println(s"not match $n")
  } // prints 21
}