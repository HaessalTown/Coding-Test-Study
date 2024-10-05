import sys
input = sys.stdin.readline

n, m, h = map(int, input().rstrip().split())
graph = [[0] * n for _ in range(h)]

for _ in range(m):
    a, b = map(int, input().rstrip().split())
    graph[a - 1][b - 1] = 1

def game():
    for i in range(n):
        temp = i
        for j in range(h):
            if graph[j][temp]:
                temp += 1
            elif temp > 0 and graph[j][temp - 1]:
                temp -= 1
        if temp != i:
            return False
    return True

def dfs(cnt, x, y):
    global ans
    if ans <= cnt:
        return
    if game():
        ans = min(ans, cnt)
        return
    if cnt == 3:
        return
    for i in range(x, h):
        if i == x:
            start = y
        else:
            start = 0
        for j in range(start, n - 1):
            if graph[i][j] == 0:
                graph[i][j] = 1
                dfs(cnt + 1, i, j + 2)
                graph[i][j] = 0

ans = 4
dfs(0, 0, 0)
if ans <= 3:
    print(ans)
else:
    print(-1)