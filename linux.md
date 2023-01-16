## sudo env var

```bash
export MY_HOST=127.0.0.1
sudo ./Launch.sh
```
Launch.sh에서 MY_HOST 환경변수를 사용한다고 가정합니다.
하지만 Launch.sh를 sudo로 실행하면 MY_HOST 환경변수를 찾을 수 없습니다.

이 때 3가지의 해결 방법이 있습니다.

---- 

1. 전체 환경 변수 전달

```bash 
sudo --preserve-env ./Launch.sh
```

`-E` 또는 `--preserve-env`를 사용하면 sudo에서 모든 환경변수를 사용할 수있습니다.  

----

2. 필요한 환경변수만 전달

```bash
sudo --preserve-env=MY_HOST ./Launch.sh 
```

전체 환경변수를 전달하는 것보다 필요한 환경변수만 전달하는게 더 안전합니다.  
원하는 환경변수 이름으로 전달할 수 있습니다.  


----

3. 환경변수 즉석으로 직접 만들기

```bash
sudo MY_HOST=127.0.0.1 ./Launch.sh 
```

환경변수를 command에서 직접 만들 수 있습니다.

