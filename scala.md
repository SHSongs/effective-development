## unapply method (추출자 패턴)

아래 코드는 list의 마지막을 찾는 함수이다
```scala
def last: T = {
  def run(xs: LIST[T]): T = xs match
	case nil: NIL[_] => throw new Error("last of empty list")
	case cons: CONS[_] if cons.tail.isEmpty => cons.head
	case cons: CONS[_] => run(cons.tail)
  run(this)
}
```
두번째 매칭을 봐보면 xs의 타입을 확인한 후 꼬리가 비었는지 확인한다.  
패턴매칭이 장황하고 판별 로직이 매칭 하나에 집중되어 가독성도 떨어진다. 


unapply를 적용시키면 패턴매칭을 간결하게 정리할 수 있다.

```scala
  def last: T = {
    def run(xs: LIST[T]): T = xs match
      case LIST(head, tail) if tail.isEmpty => head
      case LIST(_, tail) => run(tail)
      case _ => throw new Error("last of empty list")
    run(this)
  }
```
List(head, tail)을 이용해 head는 T, tail은 head를 제외한 나머지 LIST\[T\]이다. 

이제 unapply의 구현체를 살펴보자
```scala
  object LIST {
    def unapply[T](arg: LIST[T]): Option[(T, LIST[T])] = Some(arg.head, arg.tail)
  }
```
`LIST[T] => Option[(T, LIST[T]` 로 흐르는 함수이다. return type이 Option인 이유는 조건에 따라 매칭이 안되게 할 수 있기 때문이다. 예를들면 tail이 비어있다면(isEmpty) None을 반환하게 해 매칭이 성립 안하게 할 수 있다.

<br>
<br>

## 에러처리

극단적인 예시
```scala
import scala.util.Try
val v = Try(1/0).getOrElse(0)
```
