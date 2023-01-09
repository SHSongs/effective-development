
## 업을이트 된 테이블을 로컬 개발 환경에 반영할 때 어떻게 해야할까?
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