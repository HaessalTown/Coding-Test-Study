n = int(input())

array = []
for _ in range(n):
    a, b = map(int, input().split())
    array.append((a, b))

result = 0

# a를 기준으로 오름차순 정렬
array.sort()
d = [1] * 501
for i in range(n):
    for j in range(i):
        if array[i][1] > array[j][1]:
            d[i] = max(d[i], d[j] + 1)

print(n - max(d))