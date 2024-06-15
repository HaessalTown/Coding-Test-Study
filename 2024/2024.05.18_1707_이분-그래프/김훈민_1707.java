import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김훈민_1707 {
    static int t;
    static int V;
    static int E;
    static Queue<Integer> queue = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> graph;
    // 1 or 2
    static int toggle;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int testcase = 0; testcase < t; testcase++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            // graph setting
            toggle = 1;
            queue.clear();
            visited = new int[V + 1];
            graph = new ArrayList<>();
            for(int i = 0; i <= V; i++){
                graph.add(new ArrayList<>());
            }
            // 간선 정보 입력
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }
            // 1번 정점부터 start
            boolean flag = false;
            for (int i = 1; i <= V; i++) {
                if (visited[i] == 0) {
                    queue.add(i);
                    visited[i] = 1;
                    if (!bfs()) {
                        sb.append("NO\n");
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) continue;
            sb.append("YES\n");
        }
        System.out.print(sb);
    }

    static boolean bfs(){
        while(!queue.isEmpty()){
            int v = queue.poll();
            int nowToggle = visited[v];
            for(int nv : graph.get(v)){
                // 이분 그래프 x
                if(visited[nv] == nowToggle){
                    return false;
                }
                else{
                    if(visited[nv] == 0){
                        int nextToggle = doToggle(nowToggle);
                        visited[nv] = nextToggle;
                        queue.add(nv);
                    }
                }
            }
        }
        return true;
    }

    static int doToggle(int nowToggle){
        if(nowToggle == 1) return 2;
        else return 1;
    }
}