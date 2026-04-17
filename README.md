# MSA 실습 가이드

## 🐳 Docker로 실행할 때

### 시작
```bash
docker compose up
# 한 방에 다 뜸
```

### 상태 확인
```bash
docker compose ps
# 컨테이너 상태 확인
# State가 Up이면 살아있는 것
```

### 종료
```bash
docker compose down
# 컨테이너 전부 종료
```

---

## ☸️ Kubernetes로 실행할 때

```bash
$env:PATH += ';c:\minikube'
# 현재 세션에 PATH 추가

```

### 1. 클러스터 시작
```bash
minikube start
# 클러스터 다시 시작
```

### 2. 서비스 배포
```bash
kubectl apply -f k8s/
# 서비스 전부 다시 배포
```

### 3. 상태 확인
```bash
kubectl get pods
# 모든 파드 STATUS가 Running인지 확인
```

### 4. 터널 열기
```bash
minikube service api-gateway --url
# 외부에서 접근할 수 있는 URL 생성
# 이 터미널 창 닫으면 안 됨!
```

---

## 🧪 API 테스트

### 유저 생성
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"andy\",\"email\":\"andy@test.com\",\"password\":\"1234\"}"
```

### 유저 조회
```bash
curl http://localhost:8080/users
```

### 상품 생성
```bash
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"MacBook\",\"price\":\"2000000\",\"stock\":\"10\"}"
```

### 상품 조회
```bash
curl http://localhost:8080/products
```

### 주문 생성
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d "{\"userId\":\"1\",\"productId\":\"1\",\"quantity\":\"2\"}"
```

---

## ⚠️ 주의사항

> **H2 인메모리 DB 사용 중**
> 서비스 재시작하면 데이터가 초기화돼요.
> 재시작 후 유저/상품을 다시 만들어줘야 해요.

---

## 🏗️ 서비스 구조

| 서비스 | 포트 | 역할 |
|---|---|---|
| eureka-server | 8761 | 서비스 디스커버리 |
| api-gateway | 8080 | 진입점, 라우팅 |
| user-service | 8081 | 유저 관리 |
| product-service | 8082 | 상품 관리 |
| order-service | 8083 | 주문 관리 |

---

## ☸️ Kubernetes 유용한 명령어

### 파드 자동 복구 확인
```bash
kubectl delete pod <파드이름>
# 파드 강제 삭제 → 자동으로 새 파드 생성됨
```

### 스케일 아웃
```bash
kubectl scale deployment user-service --replicas=3
# user-service 파드를 3개로 늘리기
```

### 로그 확인
```bash
kubectl logs <파드이름>
# 특정 파드 로그 확인
```
