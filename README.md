MSA 실습

-- Docker로 실행할 때
docker compose up
# 한 방에 다 뜸

docker compose ps
# 컨테이너 상태 확인
# State가 Up이면 살아있는 것

-- Kubernetes로 실행할 때

minikube start
# 클러스터 다시 시작

kubectl apply -f k8s/
# 서비스 전부 다시 배포

minikube service api-gateway --url
# 터널 열기
