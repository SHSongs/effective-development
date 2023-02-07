object SomEither extends App {
  sealed abstract class SomEither[+A, +B]

  final case class SomRight[+A, +B](v: A) extends SomEither[A, B]
  final case class SomLeft[+A, +B](err: B) extends SomEither[A, B]

  def make(num: Int) = {
    if (num % 2 == 0)
      SomRight(num)
    else
      SomLeft("fail")
  }

  (0 until 9).foreach { n =>
    val a = make(n)

    a match {
      case SomRight(v) => println(v)
      case SomLeft(err) => println(s"err: $err")
    }
  }
}
