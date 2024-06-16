import sys
import heapq

input = sys.stdin.readline

n = int(input().rstrip())
array = []
for _ in range(n):
    array.append(int(input().rstrip()))

heapq.heapify(array)

result = 0

while len(array) > 1:
    first = heapq.heappop(array)
    second = heapq.heappop(array)
    result += first + second

    heapq.heappush(array, first + second)

print(result)