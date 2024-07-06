package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem11967 {
    static int N, M;
    static ArrayList<Point>[][] graph;
    static boolean[][] visited;
    static boolean[][] canGo;
    static Queue<Point> queue = new LinkedList<>();
    static int[][] vector = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N][N];
        visited = new boolean[N][N];
        canGo = new boolean[N][N];
        canGo[0][0] = true;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                graph[i][j] = new ArrayList<>();
            }
        }
        for(int j = 0; j < M; j++){
            st = new StringTokenizer(br.readLine());
            int fromX = Integer.parseInt(st.nextToken()) - 1;
            int fromY = Integer.parseInt(st.nextToken()) - 1;
            int toX = Integer.parseInt(st.nextToken()) - 1;
            int toY = Integer.parseInt(st.nextToken()) - 1;
            graph[fromX][fromY].add(new Point(toX, toY));
        }
        bfs();
        System.out.println(calcResult());
    }

    static void bfs(){
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            turnLight(p.x, p.y);
            for(int[] v : vector){
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(!canGo[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
    }

    static void turnLight(int x, int y){
        for(Point p : graph[x][y]){
            if (visited[p.x][p.y]) continue;
            if (canGo[p.x][p.y]) continue;
            canGo[p.x][p.y] = true;
            for(int[] v : vector){
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny]){
                    queue.add(new Point(p.x, p.y));
                    visited[p.x][p.y] = true;
                }
            }
        }
    }

    static int calcResult(){
        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(canGo[i][j]) result++;
            }
        }
        return  result;
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
