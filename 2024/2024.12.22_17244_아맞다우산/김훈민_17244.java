import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김훈민_17244 {
    static int N, M;
    static Point startP;
    static int keyNum;
    static char[][] graph;
    static boolean[][][] visited;
    static int[][] vector = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int binaryKey;
    static Queue<Node> queue = new LinkedList<Node>();
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        for(int i = 0; i < N; i++){
            graph[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                if(graph[i][j] == 'S'){
                    startP = new Point(i, j);
                }
                else if(graph[i][j] == 'X'){
                    graph[i][j] = (char) ('0' + keyNum);
                    keyNum++;
                }
            }
        }
        for(int i = 0; i < keyNum; i++){
            binaryKey = binaryKey | (1 << i);
        }
        visited = new boolean[N][M][binaryKey + 1];
        System.out.println(bfs());
    }

    static int bfs(){
        queue.add(new Node(startP.x, startP.y, 0));
        visited[startP.x][startP.y][0] = true;
        int time = 0;
        int size = queue.size();
        while(!queue.isEmpty()){
            for(int i = 0; i < size; i++){
                Node node = queue.poll();
                if(graph[node.x][node.y] == 'E' && node.keys == binaryKey){
                    return time;
                }
                for(int[] v : vector){
                    int nx = node.x + v[0];
                    int ny = node.y + v[1];
                    if(!isIn(nx, ny)) continue;
                    if(isWall(nx, ny)) continue;
                    int keysNum = node.keys;
                    if(isNumber(graph[nx][ny])){
                        keysNum = keysNum | (1 << (graph[nx][ny] - '0'));
                    }
                    if(visited[nx][ny][keysNum]) continue;
                    queue.add(new Node(nx, ny, keysNum));
                    visited[nx][ny][keysNum] = true;
                }
            }
            time++;
            size = queue.size();
        }
        return -1;
    }

    static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    static boolean isWall(int x, int y){
        return graph[x][y] == '#';
    }

    static boolean isIn(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Node extends Point{
        int keys;
        Node(int x, int y, int keys) {
            super(x, y);
            this.keys = keys;
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
