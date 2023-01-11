## ZIO 이펙트를 변형 또는 조합하는 방법

- 변형: effect.timeout(60.senconds)  effect에 timeout을 적용
- 조합: effect1.orElse(effect2) effect1을 실행다고 만약 실패하면 effect2 실행

### mapping: 이펙트를 변형할 때 사용.
- map, mapError

### chaining: 두 이펙트를 순차적으로 실행할 때 사용
- flatMap

### for comprehensions: flatMap과 map을 편하게 사용할 수 있음
 
### zipping: 이펙트를 결합할 때 사용 
- zip: left와 right 이펙트의 결과를 tuple로 준다 (둘 다 성공 시) 만약 한쪽이라도 실패하면 모두 실패 (tuple을 구성할 수 없으므로)
- zipRight  (\*>) , zipLeft (<\*): 한 이펙트의 결과물이 unit 같이 유용하지 않을 때 사용