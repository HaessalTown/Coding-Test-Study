import sys
import heapq
input = sys.stdin.readline

w, h = map(int, input().rstrip().split())
graph = []
c_list = []
regraph = [[None] * w for _ in range(h)]
INF = 1e9

for i in range(h):
    for j in range(w):
        regraph[i][j] = [INF, INF, INF, INF]

for i in range(h):
    graph.append(list(input().rstrip()))
    for j in range(w):
        if graph[i][j] == 'C':
            c_list.append((i, j))

def bfs(graph, x, y):
    global regraph

    heap = []
    heapq.heappush(heap, (0, 0, x, y, 0))
    regraph[x][y][0] = 0
    regraph[x][y][1] = 0
    regraph[x][y][2] = 0
    regraph[x][y][3] = 0

    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    while heap:
        mirror, rank, ax, ay, dir = heapq.heappop(heap)

        if (regraph[ax][ay][0] != 0 or regraph[ax][ay][1] != 0 or regraph[ax][ay][2] != 0 or regraph[ax][ay][3] != 0) and graph[ax][ay] == 'C':
            return min(regraph[ax][ay])

        for i in range(4):
            nx = ax + dx[i]
            ny = ay + dy[i]

            if nx < 0 or nx >= h or ny < 0 or ny >= w:
                continue
            if graph[nx][ny] == '*':
                continue

            if rank != 0 and dir != i:
                if regraph[nx][ny][i] > mirror + 1:
                    regraph[nx][ny][i] = mirror + 1
                    heapq.heappush(heap, (mirror + 1, rank + 1, nx, ny, i))
            else:
                if regraph[nx][ny][i] > mirror:
                    regraph[nx][ny][i] = mirror
                    heapq.heappush(heap, (mirror, rank + 1, nx, ny, i))

if len(c_list) > 0:
    item = c_list[0]
    print(bfs(graph, item[0], item[1]))