import org.scalatest.funsuite.AnyFunSuite

class AlgorithmSpec extends AnyFunSuite {

  val case1 = List(1, 2, List(3, 4, List(5, 6)))
  val case2 = List(1, 2, List(3, 4, List()))
  val case3 = List(1, 2, List(3, 4, 5, 6))
  val case4 = List(1, 2)
  val case5 = List(List(List(List())))
  val case6 = List()

  test("deep reverse") {
    assert(
      Algorithm.deepReverse(case1) == List(List(List(6, 5), 4, 3), 2, 1)
        && Algorithm.deepReverse(case2) == List(List(List(), 4, 3), 2, 1)
        && Algorithm.deepReverse(case3) == List(List(6, 5, 4, 3), 2, 1)
        && Algorithm.deepReverse(case4) == List(2, 1)
        && Algorithm.deepReverse(case5) == List(List(List(List())))
        && Algorithm.deepReverse(case6) == List()
    )
  }

  test("easy deep reverse") {
    assert(
      Algorithm.deepReverse2(case1) == List(List(List(6, 5), 4, 3), 2, 1)
        && Algorithm.deepReverse2(case2) == List(List(List(), 4, 3), 2, 1)
        && Algorithm.deepReverse2(case3) == List(List(6, 5, 4, 3), 2, 1)
        && Algorithm.deepReverse2(case4) == List(2, 1)
        && Algorithm.deepReverse2(case5) == List(List(List(List())))
        && Algorithm.deepReverse2(case6) == List()
    )
  }
}
