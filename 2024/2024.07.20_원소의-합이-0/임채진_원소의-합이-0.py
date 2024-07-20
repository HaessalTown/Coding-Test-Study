import sys
input = sys.stdin.readline

n = int(input().rstrip())
a = list(map(int, input().rstrip().split()))
b = list(map(int, input().rstrip().split()))
c = list(map(int, input().rstrip().split()))
d = list(map(int, input().rstrip().split()))

first = dict()
second = dict()
for i in a:
    for j in b:
        temp = i + j
        if temp in first:
            first[temp] += 1
        else:
            first[temp] = 1

for i in c:
    for j in d:
        temp = i + j
        if temp in second:
            second[temp] += 1
        else:
            second[temp] = 1

result = 0
for num in first:
    target = num * (-1)

    if target in second:
        result += first[num] * second[target]

print(result)