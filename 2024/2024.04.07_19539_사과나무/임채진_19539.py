n = int(input())
array = list(map(int, input().split()))

cnt = 0
if sum(array) % 3 == 0:
    for k in array:
        cnt += k // 2
    if cnt >= (sum(array) // 3):
        print("YES")
    else:
        print("NO")
else:
    print("NO")