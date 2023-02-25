import org.scalatest.{Suite, Suites}
import org.scalatest.funsuite.AnyFunSuite
import User._

class TreeSpec extends AnyFunSuite:
//  val sampleTree = Node("A", Some(Node("B", Some(Node("D")), Some(Node("E")))), Some(Node("C")))
  val sampleIntTree = IntNode(50, Some(IntNode(30, Some(IntNode(10)), Some(IntNode(40)))), Some(IntNode(60)))

  val sampleIndexTree = IndexNode[String](IV(50, "ㄹ"), Some(IndexNode(IV(30, "ㄴ"), Some(IndexNode(IV(10, "ㄱ"))), Some(IndexNode(IV(40, "ㄷ"))))), Some(IndexNode(IV(60, "ᆼ"))))

  val sampleUserIndexTree = IndexNode[User](iv = IV(50, User(name="ㄹ", age=100)),
    l =
      Some(
        IndexNode( iv = IV(30, User(name="ㄴ", age=200)),
          l = Some(IndexNode(IV(10, User(name="ㄱ", age=300)))),
          r = Some(IndexNode(IV(40, User(name="ㄷ", age=21))))
        )
      ),
    r = Some(IndexNode( iv = IV(60, User(name="ᆼ", age=3)))))
  //  test("preorderSpec") {
//    assert(Seq("A", "B", "D", "E", "C") === SomTree.preorder(sampleTree))
//  }
  test("find E") {
    assert(SomTree.find(40, sampleIntTree) == Some(IntNode(40)))
  }

  test("find index") {
    val expect = Some(IndexNode(IV(40, "ㄷ")))
    assert(SomTree.findIndex(40, sampleIndexTree) == expect)
  }

  test("find") {
    val expect = Some(IndexNode(IV(40, "ㄷ")))
    assert(SomTree.find("ㄷ", sampleIndexTree) == expect)
  }

  test("find User") {
    val expect = Some(IndexNode(IV(40, User(name="ㄷ", age=21))))
    assert(SomTree.find[User](User(name="ㄷ", age=21), sampleUserIndexTree).get.iv.t.name == expect.get.iv.t.name)
  }
  
  test("count age > 25"){
    val res = SomList.filter(User.makeUsers, _.age > 25)
    assert(res.length == 2)
  }


