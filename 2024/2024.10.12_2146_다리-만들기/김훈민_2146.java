import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김훈민_2146 {
    static int N;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] vector = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int result = Integer.MAX_VALUE;
    static int islandNum = 2;
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        countIsland();
        findMinBridge();
        System.out.println(result);
    }

    static void countIsland(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(graph[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    queue.clear();
                    queue.add(new Point(i, j));
                    graph[i][j] = islandNum;
                    basicBFS();
                    islandNum++;
                }
            }
        }
    }

    static void basicBFS(){
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            for(int[] v : vector){
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(graph[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                graph[nx][ny] = islandNum;
                queue.add(new Point(nx, ny));
            }
        }
    }

    static void findMinBridge(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(graph[i][j] > 1) {
                    resetVisited();
                    int temp = bfs(i, j);
                    if(temp == -1) continue;
                    result = Math.min(result, temp);
                }
            }
        }
    }

    static int bfs(int x, int y){
        int sameNumber = graph[x][y];
        visited[x][y] = true;
        queue.clear();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()){
            Point p = queue.poll();
            for(int[] v : vector){
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (graph[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, p.bridgeNum + 1));
                }
                else if (graph[nx][ny] != sameNumber) {
                    return p.bridgeNum;
                }
            }
        }
        return -1;
    }

    static void resetVisited(){
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static void showGraph(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }



    static class Point{
        int x;
        int y;
        int bridgeNum;
        Point(int x, int y, int bridgeNum){
            this.x = x;
            this.y = y;
            this.bridgeNum = bridgeNum;
        }
        Point(int x, int y){
            this.x = x;
            this.y = y;
            this.bridgeNum = 0;
        }
    }
}
