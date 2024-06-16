import sys
sys.setrecursionlimit(10000)

array = []
lop = [False] * 5

for i in range(4):
  array.append(list(map(int, list(input()))))

k = int(input())

def clockwise(idx):
  global array
  last = array[idx-1].pop()
  array[idx-1].insert(0, last)

def counterclockwise(idx):
  global array
  first = array[idx-1].pop(0)
  array[idx-1].append(first)

def rotate(idx, dir):
  prev_pole = 10
  next_pole = 10
  
  if idx > 1:
    prev_pole = array[idx - 2][2]
  if idx < 4:
    next_pole = array[idx][6]

  prev = array[idx-1][6]
  next = array[idx-1][2]
  if dir == 1: # clock
    clockwise(idx)
  elif dir == -1:
    counterclockwise(idx)

  lop[idx] = True
  if idx > 1 and not lop[idx-1] and prev_pole != prev:
    rotate(idx-1, dir*(-1))
  if idx < 4 and not lop[idx+1] and next_pole != next:
    rotate(idx+1, dir*(-1))

for _ in range(k):
  idx, dir = map(int, input().split())
  rotate(idx, dir)
  lop = [False] * 5
  
sum = 0
if array[0][0] == 1:
  sum += 1
if array[1][0] == 1:
  sum += 2
if array[2][0] == 1:
  sum += 4
if array[3][0] == 1:
  sum += 8

print(sum)
  