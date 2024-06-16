import sys
input = sys.stdin.readline

m, n = map(int, input().rstrip().split())
graph = []

d = [[0] * (n+1) for _ in range(m + 1)]
for i in range(m):
    graph.append(list(map(int, input().rstrip().split())))

result = 0
for i in range(1, m+1):
    for j in range(1, n+1):
        if graph[i-1][j-1] == 0:
            d[i][j] = min(d[i-1][j], d[i][j-1], d[i-1][j-1]) + 1
            result = max(result, d[i][j])

print(result)