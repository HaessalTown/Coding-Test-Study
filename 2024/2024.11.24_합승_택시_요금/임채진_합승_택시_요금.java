import java.util.*;

class Solution {
    public final int MAX_N = 201;
    public final int INT_MAX = Integer.MAX_VALUE;
    
    public int N;
    public int[][] graph;
    
    public long[] dijkstra(int s) {
        long[] dist = new long[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(dist, INT_MAX);
        
        dist[s] = 0;
        
        for (int i = 0; i < N; i++) {
            int minIndex = -1;
            for (int j = 0; j < N; j++) {
                if (visited[j])
                    continue;
                
                if (minIndex == -1 || dist[minIndex] > dist[j])
                    minIndex = j;
            }
            
            visited[minIndex] = true;
            
            for (int j = 0; j < N; j++) {
                if (graph[minIndex][j] == 0) {
                    continue;   
                }
                
                dist[j] = Math.min(dist[j], dist[minIndex] + graph[minIndex][j]);
            }
        }
        
        return dist;
    }
        
    public long solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        graph = new int[n][n];
        
        for (int i = 0; i < fares.length; i++) {
            int u = fares[i][0] - 1;
            int v = fares[i][1] - 1;
            graph[u][v] = fares[i][2];
            graph[v][u] = fares[i][2];
        }
        
        /*for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }*/
        
        long[] dist1 = dijkstra(s - 1);
        
        //System.out.println(Arrays.toString(dist1));
        long ans = INT_MAX;
        for (int i = 0; i < N; i++) {
            long[] dist2 = dijkstra(i);
            long cost = dist1[i] + dist2[a - 1] + dist2[b - 1];
            
            ans = Math.min(ans, (int)cost);
        }
        
        return ans;
    }
}