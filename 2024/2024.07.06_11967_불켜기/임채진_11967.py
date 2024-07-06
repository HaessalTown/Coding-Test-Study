import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
graph = [[[] for j in range(n)]  for _ in range(n)]
visited = [[False] * n for _ in range(n)]
light_map = [[False] * n for _ in range(n)]

for i in range(m):
    x, y, a, b = map(int, input().rstrip().split())
    graph[x - 1][y - 1].append((a - 1, b - 1))

cnt = 0
def bfs(graph, visited, x, y):
    global cnt

    queue = deque([])
    visited[x][y] = True
    queue.append((x, y))
    light_map[x][y] = True
    cnt += 1

    dx = [-1, 0, 1, 0]
    dy = [0, -1, 0, 1]

    while queue:
        ax, ay = queue.popleft()
        for tx, ty in graph[ax][ay]:
            if not light_map[tx][ty]:
                light_map[tx][ty] = True
                cnt += 1

                for i in range(4):
                    nx = tx + dx[i]
                    ny = ty + dy[i]

                    if 0 <= nx < n and 0 <= ny < n:
                        if visited[nx][ny]:
                            queue.append((nx, ny))

        for i in range(4):
            nx = ax + dx[i]
            ny = ay + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue

            if not visited[nx][ny] and light_map[nx][ny]:
                visited[nx][ny] = True
                queue.append((nx, ny))

bfs(graph, visited, 0, 0)

print(cnt)