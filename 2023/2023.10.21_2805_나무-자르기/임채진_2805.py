import sys
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
array = list(map(int, input().rstrip().split()))

start = 1
end = max(array)

result = 0
while start <= end:
    mid = (start + end) // 2

    total = 0
    for wood in array:
        if wood - mid > 0:
            total += wood - mid

    if total < m:
        end = mid - 1
    else:
        start = mid + 1
        result = mid

print(result)