import org.scalatest.{Suite, Suites}
import org.scalatest.funsuite.AnyFunSuite

class TreeSpec extends AnyFunSuite:
  val sampleTree = Node("A", Some(Node("B", Some(Node("D")), Some(Node("E")))), Some(Node("C")))

  test("preorderSpec") {
    assert(Seq("A", "B", "D", "E", "C") === SomTree.preorder(sampleTree))
  }

