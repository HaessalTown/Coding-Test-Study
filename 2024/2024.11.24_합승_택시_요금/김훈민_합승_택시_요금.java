import java.util.*;
import java.io.*;

class 김훈민_합승_택시_요금 {
    static int[] A, B, S;
    static final int MAX = Integer.MAX_VALUE;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] fare : fares){
            graph[fare[0]].add(new Edge(fare[1], fare[2]));
            graph[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        A = new int[n + 1];
        B = new int[n + 1];
        S = new int[n + 1];
        
        Arrays.fill(A, MAX);
        Arrays.fill(B, MAX);
        Arrays.fill(S, MAX);
        
        A = dijkstra(a, A);
        B = dijkstra(b, B);
        S = dijkstra(s, S);
        
        int answer = MAX;
        for(int i = 1; i < n + 1; i++){
            answer = Math.min(answer, A[i] + B[i] + S[i]);
        }
        return answer;
    }
    
    static int[] dijkstra(int start, int[] C){
        pq.add(new Edge(start, 0));
        C[start] = 0;
        
        while(!pq.isEmpty()){
            Edge nowE = pq.poll();
            
            if(nowE.cost > C[nowE.to]) continue;
            
            for(Edge nextE : graph[nowE.to]){
                int newCost = nextE.cost + C[nowE.to];
                
                if(C[nextE.to] > newCost){
                    C[nextE.to] = newCost;
                    pq.add(nextE);
                }
            }
        }
        return C;
    }
    
    static class Edge implements Comparable<Edge>{
        int to;
        int cost;
        
        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
}
