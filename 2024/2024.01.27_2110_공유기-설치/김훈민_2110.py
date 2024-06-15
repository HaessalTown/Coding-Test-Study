import sys

N, C = map(int, sys.stdin.readline().split())
house = []
for _ in range(N):
    house.append(int(sys.stdin.readline().rstrip()))
house.sort()
result = 0
end = house[N - 1] - house[0]
start = 1
if C == 2:
    print(house[N - 1] - house[0])
else:
    # 현재 놓은 집의 개수
    while start <= end:
        count = 0
        mid = (start + end) // 2
        # 확인하는 변수
        flag = False
        # 시작하는 집의 좌표를 넣고 시작
        temp = house[0]
        for i in range(1, N):
            if count == C - 1:
                flag = True
                break
            if house[i] >= temp + mid:
                count += 1
                temp = house[i]
        # 집을 다 놓았으면 더 넓게 놓아도 되지않을까?
        if flag or (count == C - 1):
            start = mid + 1
            result = mid
        # 집을 다 못놓았으면 더 좁게 놓아볼까?
        else:
            end = mid - 1
    print(result)
