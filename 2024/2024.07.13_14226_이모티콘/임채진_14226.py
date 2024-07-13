import sys
from collections import deque
input = sys.stdin.readline

INF = int(1e9)
s = int(input().rstrip())

def bfs(cur, visited):
    clipboard = 0
    queue = deque([])
    queue.append((cur, 0, clipboard))

    while queue:
        #print(visited)
        current, cnt, clipboard = queue.popleft()
        if current == s:
            return cnt

        for i in range(3):
            if i == 0: # 클립보드 저장
                if current != clipboard:
                    visited[current][clipboard] = cnt + 1
                    queue.append((current, cnt + 1, current))
            elif i == 1: # 이모티콘 화면에 붙여넣기
                if current + clipboard <= 1000:
                    if visited[current + clipboard][clipboard] > cnt + 1:
                        visited[current + clipboard][clipboard] = cnt + 1
                        queue.append((current + clipboard, cnt + 1, clipboard))
            elif i == 2: # 화면에 있는 이모티콘 중 하나 삭제
                if current > 0:
                    if visited[current - 1][clipboard] > cnt + 1:
                        visited[current - 1][clipboard] = cnt + 1
                        queue.append((current - 1, cnt + 1, clipboard))

visited = [[INF] * 1001 for _ in range(1001)]
print(bfs(1, visited))