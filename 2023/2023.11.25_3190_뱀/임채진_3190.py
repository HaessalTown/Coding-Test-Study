import sys
from collections import deque
input = sys.stdin.readline

n = int(input().rstrip())
k = int(input().rstrip())

graph = [[0] * n for i in range(n)]
move = deque([])
for i in range(k):
    x, y = map(int, input().rstrip().split())
    graph[x-1][y-1] = 1

l = int(input().rstrip())
for _ in range(l):
    x, c = input().rstrip().split()
    move.append((int(x), c))

sec = 0
pro_dir = 1 # 0 : 위, 1: 오른, 2: 아래, 3: 좌
def right(dir):
    return (dir + 1) % 4

def left(dir):
    cur_dir = dir - 1
    if cur_dir == -1:
        return 3
    else:
        return cur_dir

cur_x = 0
cur_y = 0

snake = deque([(0, 0)])
graph[0][0] = 2

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

while True:
    sec+=1

    if len(move) > 0 and move[0][0] + 1 == sec:
        if move[0][1] == 'L':
            pro_dir = left(pro_dir)
        elif move[0][1] == 'D':
            pro_dir = right(pro_dir)

        move.popleft()

    cur_x = cur_x + dx[pro_dir]
    cur_y = cur_y + dy[pro_dir]

    # 밖을 나감
    if cur_x < 0 or cur_x >= n or cur_y < 0 or cur_y >= n:
        break

    if graph[cur_x][cur_y] == 2: # 나랑 충돌
        break

    snake.append((cur_x, cur_y))

    if graph[cur_x][cur_y] != 1: # no 사과
        x, y = snake.popleft()
        graph[x][y] = 0

    graph[cur_x][cur_y] = 2

print(sec)