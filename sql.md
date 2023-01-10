
## 업데이트 된 테이블을 로컬 개발 환경에 반영할 때 어떻게 해야할까?
### 상황설명
1. 원본 테이블에 열이 추가됨
2. 로컬 개발 환경은 동기화 안됨
3. sql dump 도구로 insert 문을 만들어 실행시키지만 이미 데이터가 존재한다고 추가/업데이트가 안됨
4. 행 삭제 후 insert 문을 실행시키려 하지만 외래키가 있어 데이터를 날릴 수 없음

### 제약조건
외래키 무시 같은 명령을 사용할 수 없음 

### 해결방법
1. 타깃 테이블에 스키마만 생성시킨다
2. dump된 sql의 테이블 이름을 _temp 같은 접미어를 붙여준 후 sql을 실행한다.
3. join을 이용해 업데이트 시켜준다.

```sql
UPDATE human
SET simulation_types = human_temp.simulation_types
FROM human_temp
WHERE human."name" = human_temp."name";
```

### 추가정보
flyway를 사용할 수 있는 상황에서 유용하게 쓸 수 있다



## 여러 조건의 값을 찾고 싶다
PostgreSQL의 장점 중 하나인 배열 다루기 

id가 ARRAY['1111','2222','3333'] 다 것을 조회합니다. 마지막에 :: uuid[]로 타입을 명시해줄 수 있습니다.
```sql
select id, start_on from my_view
    where id=ANY(ARRAY['1111','2222','3333'] :: uuid[])
```

### 추가정보
IN 키워드를 먼저 고려해봅니다


## PostgreSQL에서 distinct를 하는데 한 열 만 하고 다른 정보는 그냥 가져오고 싶을 때

일부 열만 distinct를 하면 1:n관계가 되어 2차원 테이블로는 표현할 수 없어 에러가 발생합니다.  

이 때 distinct on () 과 order by 를 사용해 1:n 중 정렬된 하나의 n을 가져오게 할 수 있습니다.

```sql
select distinct on (id) id, period_from, period_to  from my_view
where id = '1111'
 order by id, period_from, period_to DESC
```