import scala.collection.mutable.ArrayBuffer

trait Node[T <: AnyVal]
final case class IntNode(t: Int, l: Option[IntNode] = None, r: Option[IntNode] = None) extends Node[Int]
//case object IntNode extends Node[Int]
object SomTree {

  def find(target: Int, root: IntNode): Option[IntNode] = {
    def run(node: Option[IntNode]): Option[IntNode] =
      node match
        case Some(n) if n.t == target => node
        case Some(n) if n.t > target => run(n.l)
        case Some(n) if n.t < target => run(n.r)
        case None => None

    run(Some(root))
  }

//  def preorder[T](root: Node[T]): Seq[T] = {
//    val acc = ArrayBuffer[T]()
//    def run(node: Node[T]): Unit = {
//      print(node.t)
//      acc.append(node.t)
//      if (node.l.isDefined) run(node.l.get)
//      if (node.r.isDefined) run(node.r.get)
//    }
//    run(root)
//    acc.toSeq
//  }
}
