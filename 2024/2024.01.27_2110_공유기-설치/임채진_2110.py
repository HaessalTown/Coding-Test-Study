import sys
input = sys.stdin.readline

n, c = map(int, input().rstrip().split())
array = []
for i in range(n):
    array.append(int(input().rstrip()))

array.sort()

start = 0
end = array[-1] - array[0]

result = 0
while start <= end:
    mid = (start + end) // 2

    cnt = 0
    next = 0
    for i in range(len(array)):
        if i == 0:
            cnt += 1
            next = array[0] + mid
        else:
            if array[i] >= next:
                cnt += 1
                next = array[i] + mid

    if cnt >= c:
        result = mid
        start = mid + 1
    else:
        end = mid - 1
print(result)