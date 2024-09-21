import sys
INT_MAX = sys.maxsize

n = int(input())
stamina = list(map(int, input().split()))
array = [(1, 3, 9), (1, 9, 3), (3, 1, 9), (3, 9, 1), (9, 3, 1), (9, 1, 3)]

dp = [
    [[-1] * 61 for _ in range(61)]
    for _ in range(61)
]

if len(stamina) < 3:
    stamina = stamina + [0] * (3 - len(stamina))

ans = sys.maxsize
def calculate(a, b, c):
    if a < 0:
        a = 0
    if b < 0:
        b = 0
    if c < 0:
        c = 0

    if a == 0 and b == 0 and c == 0:
        return 0

    if dp[a][b][c] != -1:
        return dp[a][b][c]

    min_val = INT_MAX
    for i in range(6):
        min_val= min(min_val, calculate(a - array[i][0], b - array[i][1], c - array[i][2]) + 1)

    dp[a][b][c] = min_val

    return min_val

ans = calculate(stamina[0], stamina[1], stamina[2])
print(ans)