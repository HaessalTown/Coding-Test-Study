import bisect
import copy

n = int(input())
array = map(int, input().split())
array2 = copy.deepcopy(array)
array2= list(set(array2))

array2.sort()

for node in array:
  index = bisect.bisect_left(array2, node)
  print(index, end=" ")