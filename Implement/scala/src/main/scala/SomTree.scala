import scala.collection.mutable.ArrayBuffer

trait Node[T] extends Ordered[T]
final case class IntNode(t: Int, l: Option[IntNode] = None, r: Option[IntNode] = None) extends Node[Int] {
  override def compare(that: Int): Int = t.compare(that)
}

final case class IV[T <: String](i: Int, t:T)

final case class IndexNode[T <: String](iv: IV[T], l: Option[IndexNode[T]] = None, r: Option[IndexNode[T]] = None) extends Node[T] {
  override def compare(that: T): Int = iv.t.compareTo(that)
}
//case object IntNode extends Node[Int]
object SomTree {

  def findIndex[T <: String](target: Int, root: IndexNode[T]): Option[IndexNode[T]] = {
    def run(node: Option[IndexNode[T]]): Option[IndexNode[T]] =
      node match
        case Some(n) if n.iv.i == target => node
        case Some(n) if n.iv.i > target => run(n.l)
        case Some(n) if n.iv.i < target => run(n.r)
        case None => None

    run(Some(root))
  }

  def find[T <: String](target: T, root: IndexNode[T]): Option[IndexNode[T]] = {
    def run(node: Option[IndexNode[T]]): Option[IndexNode[T]] =
      node match
        case Some(n) if n.iv.t == target => node
        case Some(n) if n.iv.t > target => run(n.l)
        case Some(n) if n.iv.t < target => run(n.r)
        case None => None

    run(Some(root))
  }

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
