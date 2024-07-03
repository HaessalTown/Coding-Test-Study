import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
graph = []
visited = [[0] * m for _ in range(n)]
group_list = [0] * 250001

for _ in range(n):
    graph.append(list(map(int, input().rstrip().split())))

def bfs(graph, visited, group, x, y):
    queue = deque([])
    visited[x][y] = group
    queue.append((x, y))
    cnt = 1

    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    while queue:
        ax, ay = queue.popleft()

        for i in range(4):
            nx = ax + dx[i]
            ny = ay + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m or graph[nx][ny] == 0:
                continue

            if visited[nx][ny] == 0 and graph[nx][ny] == 1:
                queue.append((nx, ny))
                visited[nx][ny] = group
                cnt += 1

    group_list[group] = cnt

ans = 0
def check(graph, visited, x, y):
    global ans
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    sum = 1
    sett = set()
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < n and 0 <= ny < m:
            if graph[nx][ny] == 1:
                sett.add(visited[nx][ny])
    for num in list(sett):
        sum += group_list[num]
    ans = max(ans, sum)

group = 1
for i in range(n):
    for j in range(m):
        if visited[i][j] == 0 and graph[i][j] == 1:
            bfs(graph, visited, group, i , j)
            group += 1

for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            check(graph, visited, i, j)

print(ans)