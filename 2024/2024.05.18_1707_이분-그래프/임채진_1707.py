import sys
from collections import deque
input = sys.stdin.readline

k = int(input().rstrip())

def bfs(graph, visited, v, start):
    queue = deque([])
    visited[start] = 0
    queue.append((start, 0))

    while queue:
        node, color = queue.popleft()

        for i in graph[node]:
            if visited[i] == color:
                return False
            if visited[i] != -1 and visited[i] != color:
                continue

            # visited[i] == -1
            if color == 0:
                visited[i] = 1
            else:
                visited[i] = 0

            queue.append((i, visited[i]))

    return True

for _ in range(k):
    v, e = map(int, input().rstrip().split())
    graph = [[] for _ in range(v + 1)]
    visited = [-1] * (v + 1)
    for i in range(e):
        u, v = map(int, input().rstrip().split())
        graph[u].append(v)
        graph[v].append(u)

    flag = True
    for i in range(1, v+1):
        if visited[i] == -1:
            flag = bfs(graph, visited, v, i)
            if not flag:
                break

    if flag:
        print("YES")
    else:
        print("NO")