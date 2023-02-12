import LIST.*

import scala.::
import scala.annotation.tailrec


sealed trait LIST[T] {
  def isEmpty = false
  def head: T
  def tail: LIST[T]

  override def equals(obj: Any): Boolean =
    @tailrec
    def listEq(a: LIST[_], b: LIST[_]): Boolean =
      if ((a.isEmpty && b.isEmpty) || a.head != b.head) {
        a.isEmpty && b.isEmpty
      }
      else {
        listEq(a.tail, b.tail)
      }

    obj match {
      case that: LIST[_] if isEmpty && that.isEmpty => true
      case that: LIST[_] => listEq(this, that)
      case _ => super.equals(obj)
    }

  def last: T = {
    @tailrec
    def run(xs: LIST[T]): T = xs match
      case LIST(head, tail) if tail.isEmpty => head
      case LIST(_, tail) => run(tail)
      case _ => throw new Error("last of empty list")
    run(this)
  }

  def length: Int = {
    @tailrec
    def run(xs: LIST[T], len: Int): Int = xs match
      case NIL(_): NIL[_] => len
      case LIST(_, tail) if tail.isEmpty =>
        len + 1
      case LIST(_, tail) =>
        run(tail, len + 1)
    run(this, 0)
  }
}

object LIST {
  def unapply[T](arg: LIST[T]): Option[(T, LIST[T])] = Some(arg.head, arg.tail)

  class CONS[T](val head: T, val tail: LIST[T]) extends LIST[T] {
    override def isEmpty: Boolean = false

    override def toString: String = f"${head} :: ${tail}"
  }

  object CONS {
    def apply[T](head: T, tail: LIST[T]): CONS[T] = new CONS(head, tail)

    def unapply[T](arg: CONS[T]): Option[(T, LIST[T])] = Some(arg.head, arg.tail)
  }

  class NIL[T] extends LIST[T] {
    override def isEmpty = true

    override def head: T = throw new Error("Nil.head")

    override def tail: LIST[T] = throw new Error("Nil.tail")

    override def toString: String = f"END"
  }
  object NIL{
    def unapply[T](arg: NIL[T]): Option[T] = None
  }
}

object SomList extends App {

  @tailrec
  def nth[T](list: LIST[T], n: Int): T = {
    println(list.head)
    list match
      case cons if cons.isEmpty => throw new IndexOutOfBoundsException()
      case cons if n == 0 => cons.head
      case cons => nth(cons.tail, n - 1)
  }

  def append[T](elem: T, l: LIST[T]): LIST[T] = {
    def run(elem: T, remains: LIST[T], acc: LIST[T]): LIST[T] = remains match
      case x: CONS[_] => append(elem, x.tail)
      case _: NIL[_] => CONS(elem, NIL())

    run(elem, l, NIL())
  }


  def init[T](xs: LIST[T]): LIST[T] = xs match
    case _: NIL[_] => throw new Error("last of empty list")
    case cons: CONS[_] if cons.tail.isEmpty => CONS(cons.head, NIL())
    case cons: CONS[_] => CONS(cons.head, init(cons.tail))

  val tasr = List(1, 3).reverse
  def reverse[T](xs: LIST[T]): LIST[T] = xs match
    case _: NIL[_] => NIL()
    case cons: CONS[_] => append(cons.head, reverse(cons.tail))

  def removeAt[T](at: Int, xs: LIST[T]): LIST[T] = xs match
    case _: NIL[_] => NIL()
    case cons: CONS[_] if at == 0 => cons.tail
    case cons: CONS[_] => CONS(cons.head, removeAt(at - 1, cons.tail))

  def combine[T](xs: LIST[T], ys: LIST[T]): LIST[T] = xs match
    case _: NIL[_] => ys
    case cons: CONS[_] => CONS(cons.head, combine(cons.tail, ys))

  def flatten(xs: Any): LIST[Any] = xs match
    case _: NIL[_] => NIL()
    case cons: CONS[_] => combine(flatten(cons.head), flatten(cons.tail))
    case _ => CONS(xs, NIL())

  def filter[T](xs: LIST[T], p: T => Boolean): LIST[T] = xs match
    case _: NIL[_] =>
      throw new Error("last of empty list")
    case cons: CONS[_] if p(cons.head) && !cons.tail.isEmpty =>
      CONS(cons.head, filter(cons.tail, p))
    case cons: CONS[_] if p(cons.head) && cons.tail.isEmpty =>
      CONS(cons.head, cons.tail)
    case cons: CONS[_] if cons.tail.isEmpty =>
      NIL()
    case cons: CONS[_] =>
      filter(cons.tail, p)

  def span[T](xs: LIST[T], p: T => Boolean): SomTuple2[LIST[T], LIST[T]] =
    val notP = (t: T) => !p(t)
    SomTuple2(filter(xs, p), filter(xs, notP))

  def filter2[T](xs: LIST[T], p: T => Boolean): LIST[T] = {

    def run(remains: LIST[T], acc: LIST[T]): LIST[T] = remains match
      case _: NIL[_] => acc
      case cons: CONS[_] if p(cons.head) => run(cons.tail, append(cons.head, acc))
      case cons: CONS[_] => run(cons.tail, acc)

    run(xs, NIL())
  }

//  def span2[T](xs: LIST[T], p: T => Boolean): SomTuple2[LIST[T], LIST[T]] =


  def pack[T](xs: LIST[T]): LIST[LIST[T]] = xs match
    case _: NIL[_] => NIL()
    case cons: CONS[_] => pack(cons.tail)

  val a = CONS("wasd", CONS("zxcv", CONS("ttt", NIL())))
  println(nth(a, 1) == "xzcv")

  println("append")
  val c = append("star", a)
  println(c)

  // init
  println("init")
  val f = init(a)
  println(a)
  println(f)

  // reverse
  println("reverse")
  val g = reverse(c)
  println(c)
  println(g)

  // removeAt
  println("removeAt")
  val h = removeAt(0, c)
  println(c)
  println(h)
  val i = removeAt(2, c)
  println(c)
  println(i)
  val j = removeAt(11, c)
  println(c)
  println(j)


  // combine
  println("combine")
  println(a)
  println(c)
  println(combine(a, c))

//   flatten
  println("flatten")
  val k = CONS("a", CONS("b", CONS("c", NIL())))
  val l = CONS("d", CONS("e", NIL()))
  val m = CONS(k, CONS(l, NIL()))
  val n = flatten(m)
  println(m)
  println(n)

  val o = init(m)
  val p = init(m)
  val q = CONS(m, CONS(o, CONS(p, NIL())))
  val r = flatten(q)
  println(q)
  println(r)

  println("span")
  val s = CONS(1, CONS(2, CONS(3,  NIL())))
  val t = span(s, _ % 2 == 0)
  println(t)

  println("filter")
  val u = filter(s, _ % 2 == 0)
  println(u)

  println("filter2")
  val u2 = filter2(s, _ % 2 == 1)
  println(u2)

  // pack List("a", "a", "b", "c") => List(List("a", 2), List("b", 1), List("c", 1))
//  println("pack")
//  val s = CONS("a", CONS("a", CONS("b", CONS("c", NIL()))))
//  val t = pack(s)
//  println(s)
//  println(t)
}


