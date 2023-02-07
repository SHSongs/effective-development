import scala.collection.mutable.ArrayBuffer

case class Node[T](t: T, l: Option[Node[T]] = None, r: Option[Node[T]] = None)

object SomTree {
  def preorder[T](root: Node[T]): Seq[T] = {
    val acc = ArrayBuffer[T]()
    def run(node: Node[T]): Unit = {
      print(node.t)
      acc.append(node.t)
      if (node.l.isDefined) run(node.l.get)
      if (node.r.isDefined) run(node.r.get)
    }
    run(root)
    acc.toSeq
  }
}
