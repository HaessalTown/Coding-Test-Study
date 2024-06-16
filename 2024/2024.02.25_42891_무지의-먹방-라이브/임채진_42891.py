import heapq

def solution(food_times, k):
    if sum(food_times) <= k:
        return -1
    
    answer = -1
    q = []
    for i in range(len(food_times)):
        heapq.heappush(q, (food_times[i], i + 1))
        
    temp = 0
    while q:
        time = q[0][0]
        if (time - temp) * len(q) <= k:
            k -= (time - temp) * len(q)
            temp = time
            heapq.heappop(q)
        else:
            q.sort(key=lambda tuple: tuple[1])
            answer = q[k % len(q)][1]
            break
  
    return answer