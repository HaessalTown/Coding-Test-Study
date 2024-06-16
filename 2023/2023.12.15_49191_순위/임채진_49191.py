INF = int(1e9)

def solution(n, results):
    graph = [[INF] * (n + 1) for i in range(n + 1)]
    
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i == j:
                graph[i][j] = 0
                
    for x, y in results:
        graph[x][y] = 1
        
    for k in range(1, n+1):
        for i in range(1, n+1):
            for j in range(1, n+1):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
    
    result = 0
    for k in range(1, n + 1):
        cnt = 0
        for i in range(1, n + 1):
            if graph[k][i] != INF or graph[i][k] != INF:
                cnt += 1
        if cnt == n:
            result += 1
                
    return result