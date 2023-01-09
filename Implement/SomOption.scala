object SomOption extends App{
  sealed abstract class SomOption[A]
  final case class SomSome[T](t: T) extends SomOption[T]
  case object SomNone extends SomOption[Nothing]

  def make(num: Int) = {
    if (num % 2 == 0)
      SomSome(num)
    else
      SomNone
  }

  (0 until 9).foreach{n =>
    val a = make(n)

    a match {
      case SomSome(v) => println(v)
      case _: SomNone.type => println("none")
    }
  }
}
