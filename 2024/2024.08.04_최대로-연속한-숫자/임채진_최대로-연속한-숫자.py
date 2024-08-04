import sys
from sortedcontainers import SortedSet
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
subtracts = list(map(int, input().rstrip().split()))

array = [(-1, n), (n + 1, n)]
array2 = [(n, -1), (n, n + 1)]

# 지워진 수 기준
s = SortedSet(array)
# 길이 기준
rev = SortedSet(array2)

for num in subtracts:
    idx = s.bisect_right((num, -1))
    
    if idx != len(s):
        front_val = s[idx - 1][0]
        last_val = s[idx][0]

        if (s[idx][1] > s[idx - 1][1]) or (s[idx][1] == s[idx - 1][1] and s[idx][0] >= s[idx - 1][0]):
            rev.remove((s[idx][1], s[idx][0]))
            rev.remove((s[idx - 1][1], s[idx - 1][0]))
        else:
            rev.remove((s[idx - 1][1], s[idx - 1][0]))
            rev.remove((s[idx][1], s[idx][0]))

        f_diff = num - front_val - 1
        l_diff = last_val - num - 1

        max_diff = max(f_diff, l_diff)

        # 해당 구간의 가장자리도 체크해서 구간 바깥 값과 비교해줘야됨
        if idx - 2 >= 0:
            f_diff = max(f_diff, s[idx - 1][0] - s[idx - 2][0] - 1)

        if idx + 1 < len(s):
            l_diff = max(l_diff, s[idx + 1][0] - s[idx][0] - 1)

        s.remove(s[idx])
        s.remove(s[idx - 1])

        s.add((front_val, f_diff))
        s.add((num, max_diff))
        s.add((last_val, l_diff))

        rev.add((f_diff, front_val))
        rev.add((max_diff, num))
        rev.add((l_diff, last_val))

        print(rev[-1][0])