n = int(input())

d = [[0] * 2 for _ in range(n)]
array = []
for _ in range(n):
    a, b = map(int, input().split())
    array.append((a, b))

d[0][0] = array[0][0]
d[0][1] = array[0][1]

for i in range(1, n):
    d[i][0] = array[i][0] + max(d[i-1][0] + abs(array[i-1][1] - array[i][1]), d[i-1][1] + abs(array[i-1][0] - array[i][1]))
    d[i][1] = array[i][1] + max(d[i-1][0] + abs(array[i-1][1] - array[i][0]), d[i-1][1] + abs(array[i-1][0] - array[i][0]))

print(max(d[n-1][0], d[n-1][1]))