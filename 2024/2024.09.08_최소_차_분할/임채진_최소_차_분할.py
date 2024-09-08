import sys
INT_MIN = -sys.maxsize

n = int(input())
nums = list(map(int, input().split()))

total = sum(nums)

dp = [INT_MIN] * (total + 1)
dp[0] = 0

for j in range(n):
    for i in range(total, -1, -1):
        if i >= nums[j]:
            if dp[i - nums[j]] == INT_MIN:
                continue
            
            dp[i] = 1

ans = sys.maxsize
for i in range(1, total):
    if dp[i] == 1:
        other = total - i 

        ans = min(ans, abs(other - i))

print(ans)