import sys
from collections import deque

input = sys.stdin.readline
MAX_INT = sys.maxsize

n = int(input())
grid = [
    list(map(int, input().split()))
    for _ in range(n)
]
world = [
    [0] * n
    for _ in range(n)
]
visited = [
    [False] * n
    for _ in range(n)
]
step = [
    [0] * n
    for _ in range(n)
]

islands = []

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def in_range(x, y):
    if x < 0 or x >= n or y < 0 or y >= n:
        return False
    return True

def is_edge_point(x, y):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if in_range(nx, ny) and grid[nx][ny] == 0:
            return True

    return False


def bfs(x, y, idx):
    # 섬의 엣지 포인트를 탐색하며 등록
    queue = deque()
    visited[x][y] = True
    world[x][y] = idx
    queue.append((x, y))

    # 섬의 엣지 포인트들이 들어감
    tlst = []

    # 엣지 포인트 여부 확인
    if is_edge_point(x, y):
        tlst.append((x, y))

    while queue:
        ax, ay = queue.popleft()

        for i in range(4):
            nx = ax + dx[i]
            ny = ay + dy[i]

            if not in_range(nx, ny):
                continue

            if not visited[nx][ny] and grid[nx][ny] == 1:
                visited[nx][ny] = True
                world[nx][ny] = idx

                # 엣지 포인트 저장
                if is_edge_point(nx, ny):
                    tlst.append((nx, ny))

                queue.append((nx, ny))

    islands.append(tlst)

def calculate_island_dist(): # bfs
    can_exit = False
    min_dist = MAX_INT

    # 맨 첫 엣지 좌표를 통해 현재 섬의 index 확인
    ex, ey = edge_queue[0]
    index = world[ex][ey]

    while edge_queue:
        x, y = edge_queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if not in_range(nx, ny):
                continue

            if not visited[nx][ny]:
                visited[nx][ny] = True
                step[nx][ny] = step[x][y] + 1

                # 다른 섬에 도착했고 내가 있는 섬이 아니면 -> exit
                if grid[nx][ny] == 1 and world[nx][ny] != index:
                    can_exit = True
                    min_dist = step[nx][ny]
                    break

                edge_queue.append((nx, ny))

        if can_exit:
            break

    # 섬 사이 길이이므로 -1
    return min_dist - 1

# 초기화
def init():
    global edge_queue
    edge_queue = deque()

    for i in range(n):
        for j in range(n):
            visited[i][j] = False
            step[i][j] = 0

# 섬의 번호 표시
world_idx = 1

# 전체 순환 -> 섬 표시
for i in range(n):
    for j in range(n):
        if not visited[i][j] and grid[i][j] == 1:
            bfs(i, j, world_idx)
            world_idx += 1

# 섬의 개수
m = len(islands)
edge_queue = deque()

# 가장 짧은 다리의 길이
ans = MAX_INT
for i in range(m):
    tlst = islands[i]

    # 값 초기화
    init()

    # bfs 필요값 집어넣기
    for j in range(len(tlst)):
        x, y = tlst[j]
        visited[x][y] = True
        edge_queue.append((x, y))

    # 섬의 최단 거리 계산
    ans = min(ans, calculate_island_dist())

print(ans)