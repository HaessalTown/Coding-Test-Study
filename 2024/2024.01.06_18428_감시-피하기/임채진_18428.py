import sys
from itertools import combinations
from copy import deepcopy
input = sys.stdin.readline

n = int(input().rstrip())
graph = []

for i in range(n):
    graph.append(list(input().rstrip().split()))

array = range(n * n)

flag = False
result = "NO"
def check(mode, graph, x, y):
    if x < 0 or x >= n or y < 0 or y >= n:
        return True
    if graph[x][y] == 'O':
        return True
    if graph[x][y] == 'S':
        return False

    # 위
    if mode == "up":
        return check("up", graph, x + 1, y)
    # 아래
    if mode == "down":
        return check("down", graph, x - 1, y)
    # 오른쪽
    if mode == "right":
        return check("right", graph, x, y + 1)
    # 왼쪽
    if mode == "left":
        return check("left", graph, x, y - 1)

for a, b, c in combinations(array, 3):
    x1 = a // n
    y1 = a % n
    x2 = b // n
    y2 = b % n
    x3 = c // n
    y3 = c % n

    if graph[x1][y1] == 'S' or graph[x1][y1] == 'T' or graph[x2][y2] == 'S' or graph[x2][y2] == 'T' or graph[x3][y3] == 'S' or graph[x3][y3] == 'T':
        continue

    vgraph = deepcopy(graph)
    vgraph[x1][y1] = 'O'
    vgraph[x2][y2] = 'O'
    vgraph[x3][y3] = 'O'

    for i in range(n):
        for j in range(n):
            if vgraph[i][j] == 'T':
                if not (check("up", vgraph, i, j) and check("down", vgraph, i, j) and check("left", vgraph, i, j) and check("right", vgraph, i, j)):
                    flag = True
                    break
        if flag:
            break

    if not flag:
        result = "YES"
        break
    flag = False

print(result)