import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
graph = []
cost_graph = []
visited = [[0] * m for _ in range(n)]

for _ in range(n):
    graph.append(list(input().rstrip()))

for _ in range(n):
    cost_graph.append(list(map(int, input().rstrip().split())))

result = 0
cnt = 1
def bfs(graph, cost_graph, visited, cnt, x, y):
    global result

    queue = deque([])
    visited[x][y] = cnt
    queue.append((x, y))

    map = {'U': (-1, 0), 'D': (1, 0), 'R': (0, 1), 'L': (0, -1)}

    star = int(1e9)
    while queue:
        ax, ay = queue.popleft()
        dx, dy = map[graph[ax][ay]]

        nx = ax + dx
        ny = ay + dy

        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            return

        if visited[nx][ny] == 0:
            visited[nx][ny] = cnt
            queue.append((nx, ny))
        else:
            if visited[nx][ny] == cnt:
                star = min(star, cost_graph[nx][ny])
                visited[nx][ny] = cnt * (-1)
                queue.append((nx, ny))
            elif visited[nx][ny] == cnt * (-1):
                result += star
                return
            else:
                return

for i in range(n):
    for j in range(m):
        if visited[i][j] == 0:
            bfs(graph, cost_graph, visited, cnt, i, j)
            cnt += 1

print(result)