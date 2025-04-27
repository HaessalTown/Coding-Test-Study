import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 김훈민_1414 {
    static int N;
    static int[] parents;
    static int[][] graph;
    static Edge[] edges;
    static int MAX_EDGE_NUM;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        parents = new int[N + 1];
        for(int i = 1; i < N+1; i++)
            parents[i] = i;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                if(i == j) {
                    int temp = chg(input.charAt(j));
                    if(temp != 0) result += chg(input.charAt(j));
                    continue;
                }
                if(input.charAt(j) != '0') {
                    graph[i][j] = chg(input.charAt(j));
                    result += graph[i][j];
                    cnt++;
                }
            }
        }

        MAX_EDGE_NUM = cnt;
        edges = new Edge[MAX_EDGE_NUM];
        cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j || graph[i][j] == 0) continue;
                edges[cnt++] = new Edge(i, j, graph[i][j]);
            }
        }

        Arrays.sort(edges);
        cnt = 0;
        int sum = 0;
        for(int i = 0; i < MAX_EDGE_NUM; i++){
            if(cnt == N-1) break;
            if(union(edges[i].from, edges[i].to)){
                cnt++;
                sum += edges[i].dist;
            }
        }
        if (cnt != N - 1) {
            System.out.println(-1);
            return;
        }
        System.out.println(result - sum);

    }

    static boolean union(int i, int j){
        int x = find(i);
        int y = find(j);
        if(x == y) return false;
        parents[x] = y;
        return true;
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static int chg(char a){
        if(a == '0') return 0;
        if(a < 'a') return a - 'A' + 27;
        return a - 'a' + 1;
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", dist=" + dist +
                    '}';
        }
    }
}
