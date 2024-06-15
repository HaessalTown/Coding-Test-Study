import sys


def binary_search(x):
    start = 0
    end = len(sort_num_list) - 1
    while start <= end:
        mid = (start + end) // 2
        target = sort_num_list[mid]
        if target == x:
            return mid
        elif target > x:
            end = mid - 1
        else:
            start = mid + 1


N = int(input())
num_list = list(map(int, sys.stdin.readline().split()))
sort_num_list = list(set(num_list))
sort_num_list.sort()
for i in range(N):
    result = binary_search(num_list[i])
    if i == N - 1:
        print(result, end="")
        continue
    print(result, end=" ")