import org.scalatest.{Suite, Suites}
import org.scalatest.funsuite.AnyFunSuite

class TreeSpec extends AnyFunSuite:
//  val sampleTree = Node("A", Some(Node("B", Some(Node("D")), Some(Node("E")))), Some(Node("C")))
  val sampleIntTree = IntNode(50, Some(IntNode(30, Some(IntNode(10)), Some(IntNode(40)))), Some(IntNode(60)))

  //  test("preorderSpec") {
//    assert(Seq("A", "B", "D", "E", "C") === SomTree.preorder(sampleTree))
//  }
  test("find E") {
    assert(SomTree.find(40, sampleIntTree) == Some(IntNode(40)))
  }

  test("count age > 25"){
    val res = SomList.filter(User.makeUsers, _.age > 25)
    assert(res.length == 2)
  }

