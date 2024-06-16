from collections import deque

sum = 0
n = int(input())

buildings = []
deque = deque()

for _ in range(n):
  buildings.append(int(input()))

for i in range(n):
  while len(deque) > 0: # 거꾸로    
    if deque[-1] <= buildings[i]:
      deque.pop()
    else:
      sum += len(deque)
      break

  deque.append(buildings[i])

print(sum)