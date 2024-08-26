import sys
import heapq
input = sys.stdin.readline

n = int(input().rstrip())
array = []
for _ in range(n):
    array.append(int(input().rstrip()))

median = array[0]
max_pq, min_pq = [], []
print(median)

for i in range(1, n):
    if i % 2 == 1: # 짝수
        if array[i] < median:
            heapq.heappush(max_pq, -array[i])
        else:
            heapq.heappush(min_pq, array[i])

        if i == 1:
            print(min(array[0], array[1]))
            continue

        if max_pq and min_pq:
            if array[i] < median:
                median2 = min(-max_pq[0], median)
            else:
                median2 = min(min_pq[0], median)

        print(median2)
    else: # 홀수
        if len(max_pq) > len(min_pq):
            candidate = -heapq.heappop(max_pq)
        else:
            candidate = heapq.heappop(min_pq)

        nums = sorted([median, array[i], candidate])

        heapq.heappush(max_pq, -nums[0])
        median = nums[1]
        heapq.heappush(min_pq, nums[2])

        print(median)