import sys
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
array = list(map(int, input().rstrip().split()))

prefix_sum = [0] * (n + 2)


for _ in range(m):
    a, b, k = map(int, input().rstrip().split())

    prefix_sum[a] += k
    prefix_sum[b + 1] += -k

sum_val = 0
for i in range(1, n + 1):
    sum_val += prefix_sum[i]
    prefix_sum[i] = sum_val

for i in range(1, n + 1):
    prefix_sum[i] += array[i - 1]
    print(prefix_sum[i], end=" ")