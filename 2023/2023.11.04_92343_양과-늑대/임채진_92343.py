left = [-1] * 20 # 왼쪽 자식 저장
right = [-1] * 20 # 오른쪽 자식 저장
dump = []
n = 0
ans = 1
visited = [0] * (1 << 17)

def algo(state):
    global ans
    if visited[state]: return None # 참이면 종료
    visited[state] = 1
    wolf, num = 0, 0
    for i in range(n):
        if state & (1 << i): # 해당 위치를 방문한다는 뜻 eg. 100000 & 100
            num += 1
            wolf += dump[i] # 늑대는 1이니까 그냥 리스트에서 더함
    if 2*wolf >= num: return None
    ans = max(ans, num - wolf)
    
    for i in range(n):
        if not state & (1<<i):
            continue
        if left[i] != -1:
            algo(state | (1<<left[i]))
        if right[i] != -1:
            algo(state | (1<<right[i]))
    

def solution(info, edges):
    global n, val
    n = len(info) # 노드 개수
    dump = info[:]
    for u, v in edges:
        if left[u] == -1:
		 left[u] = v
        else:
		 right[u] = v
    
    algo(1)
    return ans