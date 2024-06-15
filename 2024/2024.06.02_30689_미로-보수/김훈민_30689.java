import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 김훈민_30689 {
    static int N, M;
    static char[][] graph;
    static int[][] cost;
    /**
     * 0 => 아직 방문 안한 경우
     * 1 => 미로 탈출 못하는 경우
     * 2 => 미로 탈출 가능한 경우
     */
    static int[][] canGoOut;
    static boolean[][] visited;
    // U D L R
    static int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Stack<Point> stack = new Stack<>();
    static int result = 0;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        cost = new int[N][M];
        canGoOut = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            graph[i] = br.readLine().toCharArray();
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 탐색 시작
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                // 방문안한 경우 탐색 시작
                if(canGoOut[i][j] == 0){
                    // true 면 해당 경우는 탈출가능한 경우
                    if(goMaze(i, j)){
                        while(!stack.isEmpty()){
                            Point p = stack.pop();
                            canGoOut[p.x][p.y] = 2;
                        }
                    }
                    // false 면 탈출 불가능 => 점프대 설치 필요
                    else{
                        int jumpX = -1, jumpY = -1;
                        int jumpCost = Integer.MAX_VALUE;
                        while(!stack.isEmpty()){
                            Point p = stack.pop();
                            // 싸이클 안에서만 점프대를 설치해야한다
                            if(canGoOut[p.x][p.y] == 2){
                                break;
                            }
                            canGoOut[p.x][p.y] = 2;
                            if(jumpCost > cost[p.x][p.y]){
                                jumpX = p.x;
                                jumpY = p.y;
                                jumpCost = cost[p.x][p.y];
                            }
                        }
                        result += jumpCost;
                        // 점프대 설치
                        graph[jumpX][jumpY] = 'Z';
                        // 스택에 남은 녀석들 처리
                        while (!stack.isEmpty()){
                            Point p = stack.pop();
                            canGoOut[p.x][p.y] = 2;
                        }
                    }
                }
            }
        }
        System.out.println(result);

    }

    static boolean goMaze(int x, int y){
        canGoOut[x][y] = 1;
        stack.add(new Point(x,y));
        int dir = findDir(graph[x][y]);
        // 점프대
        if(dir == 4) return true;
        int nx = x + vector[dir][0];
        int ny = y + vector[dir][1];
        while(true){
            // 어차피 나갈 수 있는 경우와 미로 탈출한 경우
            if(!isIn(nx, ny) || canGoOut[nx][ny] == 2){
                // 지금까지 탐색했던 부분들은 저장 필요
                return true;
            }
            // loop 에 빠진 경우
            if(canGoOut[nx][ny] == 1){
                stack.add(new Point(nx,ny));
                // 미로를 탈출할 수 없으므로 false
                return false;
            }
            canGoOut[nx][ny] = 1;
            stack.add(new Point(nx,ny));
            dir = findDir(graph[nx][ny]);
            // 점프대를 만난 경우
            if(dir == 4) return true;
            nx += vector[dir][0];
            ny += vector[dir][1];
        }
    }

    // 아직 미로 안에 있는지?
    static boolean isIn(int x, int y){
        if(x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }

    // char -> dir 로
    static int findDir(char c){
        if(c == 'U') return 0;
        else if(c == 'D') return 1;
        else if(c == 'L') return 2;
        else if(c == 'R') return 3;
        // 점프대가 설치된 경우
        else return 4;
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}