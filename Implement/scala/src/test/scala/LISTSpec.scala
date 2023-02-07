import LIST.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{Suite, Suites}

class LISTSpec extends AnyFunSuite {
  val sample = CONS("a", CONS("b", CONS("c", NIL())))

  test("equal") {
    assert(NIL() == NIL())
  }
  test("equal2") {
    assert(sample == sample)
  }
  test("equal3") {
    assert(sample.tail != sample)
  }
  test("extract") {
    val (head, tail) = sample match
      case LIST(head, tail) => (head, tail)
      case _ => ("", NIL())
    assert(head == "a" && tail == CONS("b", CONS("c", NIL())))
  }
  test("last") {
    assert(sample.last == "c")
  }
}
