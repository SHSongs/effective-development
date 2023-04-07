import scala.annotation.tailrec

object Algorithm {

  def deepReverse(l: List[Any]): List[Any] = {
    def run(l: List[Any], acc: List[Any]): List[Any] = l match {
      case head :: next =>
        head match {
          case inList: List[_] =>
            inList match {
              case inHead :: inNext =>
                run(next, run(inNext, inHead :: Nil) :: acc)
              case Nil =>
                run(next, List() :: acc)
            }
          case _: Any => run(next, head :: acc)
        }
      case Nil => acc
    }
    run(l, List.empty[Any])
  }

  def deepReverse2(l: List[Any]): List[Any] = {
    def run(l: List[Any], acc: List[Any]): List[Any] = l match {
      case ::(head, next) =>
        println(l)
        head match {
          case inList: List[_] =>
            run(next, run(inList, Nil) :: acc)
          case _: Any =>
            run(next, head :: acc)
        }
      case Nil => acc
    }

    run(l, List.empty[Any])
  }

}
