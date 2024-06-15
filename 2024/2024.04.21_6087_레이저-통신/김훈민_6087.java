import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 김훈민_6087 {
    static int W, H;
    static char[][] graph;
    static int[][][] visited;
    static PriorityQueue<P_Point> pqueue = new PriorityQueue<>();
    static Point[] c_position = new Point[2];
    static int[][] vector = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 입력 받기
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new char[H][W];
        visited = new int[H][W][4];
        int cnt = 0;
        for(int i = 0; i < H; i++){
            graph[i] = br.readLine().toCharArray();
            for(int j = 0; j < W; j++){
                // visited 배열 초기화
                for(int z = 0; z < 4; z++){
                    visited[i][j][z] = Integer.MAX_VALUE;
                }
                if(graph[i][j] == 'C'){
                    c_position[cnt] = new Point(i, j);
                    cnt++;
                }
            }
        }
        bfs();
        System.out.println(result);
    }

    // bfs
    static void bfs(){
        for(int i = 0; i < 4; i++){
            visited[c_position[0].x][c_position[0].y][i] = 0;
            pqueue.add(new P_Point(c_position[0].x, c_position[0].y, i, 0));
        }
        while(!pqueue.isEmpty()){
            P_Point p = pqueue.poll();
            // 도착
            if(p.x == c_position[1].x && p.y == c_position[1].y){
                result = Math.min(result, p.count);
                continue;
            }
            for(int i = 0; i < 4; i++){
                int nx = p.x + vector[i][0];
                int ny = p.y + vector[i][1];
                // 벗어나면 아웃
                if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                // 벽이면 아웃
                if(graph[nx][ny] == '*') continue;
                // count 증가
                int n_count = p.count;
                // 처음이 아니거나 이전값이랑 다르면 거울 설치 횟수 증가
                if(p.pre != i) n_count++;
                // visited 처음 max 초기화 => 사용한 거울횟수가 작거나 같으면 고고
                if(visited[nx][ny][i] > n_count){
                    // 방향 변경
                    pqueue.add(new P_Point(nx, ny, i, n_count));
                    visited[nx][ny][i] = n_count;
                }
            }
        }
    }

    // Point class
    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class P_Point extends Point implements Comparable {
        int pre;
        int count;

        P_Point(int x, int y, int pre, int count) {
            super(x, y);
            this.pre = pre;
            this.count = count;
        }

        @Override
        public int compareTo(Object o) {
            P_Point other = (P_Point) o;
            return this.count - other.count;
        }
    }

}