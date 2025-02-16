import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김훈민_1368 {
    static int N;
    static int[] parents;
    static Edge[] edges;
    static int MAX_EDGE_NUM;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MAX_EDGE_NUM = (N+1)*N/2;
        parents = new int[N + 1];
        for(int i = 0; i < N + 1; i++){
            parents[i] = i;
        }
        edges = new Edge[MAX_EDGE_NUM];
        int cnt = 0;
        for(int i = 0; i < N; i++){
            edges[cnt++] = new Edge(i, N, Integer.parseInt(br.readLine()));
            pq.add(edges[cnt-1]);
        }
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i + 1; j++){
                st.nextToken();
            }
            for(int j = i + 1; j < N; j++){
                edges[cnt++] = new Edge(i, j, Integer.parseInt(st.nextToken()));
                pq.add(edges[cnt-1]);
            }
        }
        cnt = 0;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(union(e.to, e.from)){
                result += e.weight;
                cnt++;
            }
            if(cnt == N) break;
        }
        System.out.println(result);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        parents[b] = a;
        return true;
    }

    static int find(int n){
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
