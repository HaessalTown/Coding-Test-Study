import sys
input = sys.stdin.readline
INF = 1e9

n, h = map(int, input().rstrip().split())
obs = []
height = [0] * (h + 2)
for i in range(1, n + 1):
    ins = int(input().rstrip())

    if i % 2 == 1: # 석순
        height[1] += 1
        height[ins + 1] -= 1
    else: # 종유석
        height[h + 1] -= 1
        height[h - ins + 1] += 1

for i in range(1, h + 2):
    height[i] = height[i - 1] + height[i]

result = INF
cnt = 0
for i in range(1, h + 1):
    if result > height[i]:
        result = height[i]
        cnt = 1
    elif result == height[i]:
        cnt += 1

print(result, cnt)