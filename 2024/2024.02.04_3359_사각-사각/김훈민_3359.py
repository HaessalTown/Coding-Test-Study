import sys

n = int(sys.stdin.readline().rstrip())
dp = [[] for _ in range(n)]
l_a = 0
l_b = 0
for i in range(n):
    a, b = map(int, sys.stdin.readline().split())
    if i == 0:
        dp[i].append(a)
        dp[i].append(b)
        l_a = a
        l_b = b
        continue
    dp[i].append(max(dp[i - 1][0] + a + abs(l_b - b), dp[i - 1][1] + a + abs(l_a - b)))
    dp[i].append(max(dp[i - 1][0] + b + abs(l_b - a), dp[i - 1][1] + b + abs(l_a - a)))
    l_a = a
    l_b = b
print(max(dp[n - 1][0], dp[n - 1][1]))