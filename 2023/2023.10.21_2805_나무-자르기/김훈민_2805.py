import sys


N, M = map(int, input().split())
tree = list(map(int, sys.stdin.readline().split()))
start = 1
end = max(tree)
result = 0
while start <= end:
    middle = (start + end) // 2
    temp = 0
    for i in range(N):
        if tree[i] > middle:
            temp = temp + tree[i] - middle
    if temp >= M:
        result = middle
        start = middle + 1
    else:
        end = middle - 1
print(result)
