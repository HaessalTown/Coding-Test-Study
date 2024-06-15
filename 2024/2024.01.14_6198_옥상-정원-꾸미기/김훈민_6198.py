import sys
from collections import deque

N = int(sys.stdin.readline().rstrip())
num_list = deque()
stack_len = 0
result = 0
for _ in range(N):
    now_input = int(sys.stdin.readline().rstrip())
    if stack_len == 0:
        num_list.append(now_input)
        stack_len += 1
        continue
    if num_list[stack_len - 1] <= now_input:
        while num_list:
            if num_list[stack_len - 1] <= now_input:
                num_list.pop()
                stack_len -= 1
            else:
                result += stack_len
                num_list.append(now_input)
                stack_len += 1
                break
        if stack_len == 0:
            num_list.append(now_input)
            stack_len += 1
    else:
        result += stack_len
        num_list.append(now_input)
        stack_len += 1
print(result)