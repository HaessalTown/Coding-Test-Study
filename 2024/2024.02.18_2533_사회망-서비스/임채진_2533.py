import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input().rstrip())

graph = [[] for _ in range(n + 1)]
visited = [0] * (n + 1)
for _ in range(n - 1):
    u, v = map(int, input().rstrip().split())
    graph[u].append(v)
    graph[v].append(u)

cnt = 0
def dfs(graph, visited, start):
    global cnt
    visited[start] = 1

    flag = 1
    for i in graph[start]:
        if visited[i] == 0:
            res = dfs(graph, visited, i)
            flag = max(flag, res)

    if (flag == 1 and len(graph[start]) > 1) or flag == 3:
        cnt += 1
        visited[start] = 2
    elif flag:
        visited[start] = 3

    return visited[start]

dfs(graph, visited, 1)
print(cnt)