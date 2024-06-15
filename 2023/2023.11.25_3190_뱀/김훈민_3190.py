# 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
# 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
# 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
# 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
import sys
from collections import deque


def change_direction(s, now_dir):
    if s == 'D':
        # 위로 가고 있었다면
        if now_dir == vector[0]:
            # 오른쪽
            now_dir = vector[3]
        # 아래로 가고 있었다면
        elif now_dir == vector[1]:
            # 왼쪽
            now_dir = vector[2]
        # 좌측으로 가고 있었다면
        elif now_dir == vector[2]:
            # 위로
            now_dir = vector[0]
        # 우측으로 가고 있었다면
        elif now_dir == vector[3]:
            # 아래로
            now_dir = vector[1]
    elif s == 'L':
        # 위로 가고 있었다면
        if now_dir == vector[0]:
            # 왼쪽으로
            now_dir = vector[2]
        # 아래로 가고 있었다면
        elif now_dir == vector[1]:
            # 우측으로
            now_dir = vector[3]
        # 좌측으로 가고 있었다면
        elif now_dir == vector[2]:
            # 아래로
            now_dir = vector[1]
        # 우측으로 가고 있었다면
        elif now_dir == vector[3]:
            # 위로
            now_dir = vector[0]
    return now_dir


N = int(input())
# graph 0 으로 초기화
graph = [[0 for _ in range(N)] for _ in range(N)]
# 뱀의 위치는 항상 좌측 상단 이므로
graph[0][0] = 2

K = int(input())
# 사과 위치 1로 변경
for _ in range(K):
    a, b = map(int, sys.stdin.readline().split())
    graph[a - 1][b - 1] = 1

# 방향 전환 정보 큐
queue = deque()
L = int(input())

# 뱀 위치 정보 큐
queue_snk = deque()

# 뱀 위치 저장
queue_snk.append((0, 0))
snake_x = 0
snake_y = 0
# 방향 전환 정보 넣기
for _ in range(L):
    a1, b1 = map(str, sys.stdin.readline().split())
    a1 = int(a1)
    queue.append((a1, b1))

# 방향 벡터 (상, 하, 좌, 우)
vector = [[-1, 0], [1, 0], [0, -1], [0, 1]]
# 초기 방향은 오른쪽
now_v = vector[3]
# 시작 초
count = 0
# 결과 저장할 변수
result = 0
# 초기 변경해야하는 방향 값
check_count, n_direction = queue.popleft()
L -= 1

# 뱀의 길이
len_snake = 1

while True:
    # 초 증가
    count += 1

    # 현재 방향대로 전진
    nx = now_v[0]
    ny = now_v[1]

    # 다음 뱀의 머리 위치
    n_snake_x = snake_x + nx
    n_snake_y = snake_y + ny

    # 만약 벽에 박거나 뱀 몸에 박으면 게임 끝
    if n_snake_x < 0 or n_snake_x >= N or n_snake_y < 0 or n_snake_y >= N or (n_snake_x, n_snake_y) in queue_snk:
        result = count
        break
    # 사과가 없는 평범한 칸이라면
    if graph[n_snake_x][n_snake_y] == 0:
        a, b = queue_snk.popleft()
        graph[a][b] = 0
    # 뱀의 위치 갱신
    snake_x = n_snake_x
    snake_y = n_snake_y
    graph[n_snake_x][n_snake_y] = 2
    queue_snk.append((n_snake_x, n_snake_y))

    # 방향 변경해야하는 초면
    if count == check_count and L != -1:
        # 방향 전환 하고
        now_v = change_direction(n_direction, now_v)
        # 방향 값 갱신
        if L != 0:
            check_count, n_direction = queue.popleft()
        L -= 1

# 다 진행했다면 출력
print(result)