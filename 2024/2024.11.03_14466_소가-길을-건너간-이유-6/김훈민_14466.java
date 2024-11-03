import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김훈민_14466 {
    static int N, K, R;
    static int[][] graph;
    // 상 하 좌 우
    static boolean[][][] cantGo;
    static int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] parents;
    static int vNum;
    static int result;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        vNum = N * N;
        parents = new int[vNum];
        for(int i = 0; i < vNum; i++) parents[i] = i;
        graph = new int[N][N];
        cantGo = new boolean[N][N][4];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            if(x1 == x2){
                if(y1 < y2) {
                    cantGo[x1][y1][3] = true;
                    cantGo[x2][y2][2] = true;
                }
                else{
                    cantGo[x2][y2][3] = true;
                    cantGo[x1][y1][2] = true;
                }
            }
            else{
                if(x1 < x2){
                    cantGo[x1][y1][1] = true;
                    cantGo[x2][y2][0] = true;
                }
                else{
                    cantGo[x2][y2][1] = true;
                    cantGo[x1][y1][0] = true;
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < 4; k++){
                    int nx = i + vector[k][0];
                    int ny = j + vector[k][1];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if(cantGo[i][j][k]) continue;
                    union(find(i * N + j), find(nx * N + ny));
                }
            }
        }
        Point[] cows = new Point[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            cows[i] = new Point(x1, y1);
        }
        for(int i = 0; i < K; i++){
            for(int j = i + 1; j < K; j++){
                if(find(cows[i].x * N + cows[i].y) != find(cows[j].x * N + cows[j].y)) result++;
            }
        }
        System.out.println(result);
    }

    static int find(int n){
        if(parents[n] == n)
            return n;

        return parents[n] = find(parents[n]);
    }

    static void union(int a, int b){
        int x = find(a);
        int y = find(b);
        if(x != y){
            parents[x] = y;
        }
    }

    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
