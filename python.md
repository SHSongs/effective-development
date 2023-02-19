## AttributeError: 'str' object has no attribute 'id'
python에서 때때로 발생하는 에러이다.  
이 에러는 런타임에서 발생한다. 런타임에 이런 문제가 터지는 현상은 약타입언어의 단점 중 하나이다.

### 문제가 생기는 이유  
object.id를 호출했지만 object에는 id가 없어 발생한다.  

### 문제의 발생 원인은 다양하다 
1. object의 id 메소드가 없다.
2. object가 dict인데 dataclass 혹은 get set이 있다고 착각했을 때. 이 경우는 object['id']로 불러온다.
3. id가 없을 때. 사용하는 라이브러리에서 이 문제가 발생하면 가끔씩 버전이 업데이트 되면서 호출하는 메소드의(여기서는 id) 이름이 변경될 수 있다
