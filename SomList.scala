import scala.annotation.tailrec

object SomList extends App {
  trait LIST[T]{
    def isEmpty = false
    def head: T
    def tail: LIST[T]
  }

  class CONS[T] (val head: T, val tail: LIST[T]) extends LIST[T]{
    override def isEmpty: Boolean = false
  }

  class NIL[T] extends LIST[T] {
    override def isEmpty = true
    override def head: T = throw new Error("Nil.head")
    override def tail: LIST[T] = throw new Error("Nil.tail")
  }

  def nth[T](list: LIST[T], n: Int): T = {
    println(list.head)
    list match
      case cons if cons.isEmpty => throw new IndexOutOfBoundsException()
      case cons if n == 0 => cons.head
      case cons => nth(cons.tail, n - 1)
  }
  val a = CONS("wasd", CONS("zxcv", CONS("ttt", NIL())))
  println(nth(a, 1) == "xzcv")
}
