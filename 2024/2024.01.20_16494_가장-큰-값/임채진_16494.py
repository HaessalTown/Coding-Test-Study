import sys
from itertools import combinations
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
array = list(map(int, input().rstrip().split()))

d = [[0] * n for _ in range(n)]

for i in range(n):
    d[i][i] = array[i]
    for j in range(i, n):
       d[i][j] = max(d[i][j-1] + array[j], array[j])

result = int(1e9) * (-1)

for temp in combinations(list(range(n-1)), m - 1):
    sum = 0
    prev = 0
    for k in range(len(list(temp))):
        sum += max(d[prev][prev:temp[k]+1])
        prev = temp[k] + 1
    sum += max(d[prev][prev:n])

    result = max(result, sum)
print(result)