from collections import deque

n = int(input())

graph = []

for i in range(n):
    graph.append(list(input()))

visited = []

b_entry = []
e_entry = []
for i in range(n):
    for j in range(n):
        if graph[i][j] == 'B':
            b_entry.append((i, j))
        elif graph[i][j] == 'E':
            e_entry.append((i, j))

def get_next_pos(graph, pos):
    next = []

    n = len(graph)
    dx = [0, -1, 0, 1]
    dy = [1, 0, -1, 0]

    pos.sort()

    x1 = pos[0][0]
    y1 = pos[0][1]
    x2 = pos[1][0]
    y2 = pos[1][1]
    x3 = pos[2][0]
    y3 = pos[2][1]


    for i in range(4):
        nx1 = x1 + dx[i]
        ny1 = y1 + dy[i]
        nx2 = x2 + dx[i]
        ny2 = y2 + dy[i]
        nx3 = x3 + dx[i]
        ny3 = y3 + dy[i]

        if nx1 < 0 or nx1 >= n or nx2 < 0 or nx2 >= n or nx3 < 0 or nx3 >= n or ny1 < 0 or ny1 >= n or ny2 < 0 or ny2 >= n or ny3 < 0 or ny3 >= n:
            continue

        if graph[nx1][ny1] == '1' or graph[nx2][ny2] == '1' or graph[nx3][ny3] == '1':
            continue

        next.append({(nx1, ny1), (nx2, ny2), (nx3, ny3)})

    if x1 == x2 == x3: #가로
        if (x1 - 1 >= 0 and (graph[x1-1][y1] != '1' and graph[x2-1][y2] != '1' and graph[x3-1][y3] != '1')) and (x1 + 1 < n and (graph[x1+1][y1] != '1' and graph[x2+1][y2] != '1' and graph[x3+1][y3] != '1')):
            next.append({(x2 - 1, y2), (x2, y2), (x2 + 1, y2)})
    if y1 == y2 == y3:
        if (y1 - 1 >= 0 and (graph[x1][y1-1] != '1' and graph[x2][y2-1] != '1' and graph[x3][y3-1] != '1')) and (y1 + 1 < n and (graph[x1][y1+1] != '1' and graph[x2][y2+1] != '1' and graph[x3][y3+1] != '1')):
            next.append({(x2, y2-1), (x2, y2), (x2, y2+1)})

    return next


def bfs(graph, visited, start, end):
    queue = deque([])
    visited.append(start)
    queue.append((start, 0))

    result = 0
    while queue:
        pos, rank = queue.popleft()
        if pos == end:
            result = rank
            break

        for poss in get_next_pos(graph, list(pos)):
            if poss not in visited:
                visited.append(poss)
                queue.append((poss, rank + 1))

    return result

answer = bfs(graph, visited, set(b_entry), set(e_entry))
print(answer)