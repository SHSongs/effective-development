final case class SomTuple2[+T1, +T2](_1: T1, _2: T2) extends Product2[T1, T2] {
  override def toString: String = s"(\"${_1}\",\"${_2}\")"
}

final case class SomTuple3[+T1, +T2, +T3](_1: T1, _2: T2, _3: T3) extends Product3[T1, T2, T3] {
  override def toString: String = s"(\"${_1}\",\"${_2},\"${_3}\")"
}

object SomTupleApp extends App {

  val pair = SomTuple3(123, "dis", 0.3f)
  println(pair)

//  val (a, b, c) = pair
//  println(a)
//  println(b)
//  println(c)
}
