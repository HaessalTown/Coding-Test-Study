import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김훈민_18428 {
    static char[][] graph;
    static int N;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        ArrayList<Point> blank_list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                char temp = st.nextToken().charAt(0);
                // blank 라서 장애물을 놓을 수 있음
                if(temp == 'X'){
                    blank_list.add(new Point(i, j));
                }
                graph[i][j] = temp;
            }
        }
//        for(char[] t : graph){
//            System.out.println(Arrays.toString(t));
//        }
        int blank_list_len = blank_list.size();
        // 3개의 포인터로 돌리면서 어디에 놓을지 탐색
        for(int i = 0; i < blank_list_len; i++){
            Point p1 = blank_list.get(i);
            for(int j = i + 1; j < blank_list_len; j++){
                Point p2 = blank_list.get(j);
                for(int k = j + 1; k < blank_list_len; k++){
                    Point p3 = blank_list.get(k);
                    // 3개의 좌표에 장애물 놓기
                    graph[p1.x][p1.y] = 'B';
                    graph[p2.x][p2.y] = 'B';
                    graph[p3.x][p3.y] = 'B';
                    // 다 숨는게 가능하면
                    if(verify()){
                        System.out.println("YES");
                        System.exit(0);
                    }
                    // 불가능하면 계속 진행 위해서 원복
                    graph[p1.x][p1.y] = 'X';
                    graph[p2.x][p2.y] = 'X';
                    graph[p3.x][p3.y] = 'X';
                }
            }
        }
        // 다 돌았는데 못피하면
        System.out.println("NO");
    }

    static boolean verify(){
        // 가로 검증
        for(int i = 0; i < N; i++){
            String s = "";
            for(int j = 0; j < N; j++){
                // 비어있으면 넘어가고
                if(graph[i][j] == 'X'){
                    continue;
                }
                // S 또는 T 또는 B는 넣고
                s = s + graph[i][j];
            }
            // 이렇게 붙어있으면 안돼
            if(s.contains("ST") || s.contains("TS")){
                return false;
            }
        }
        // 세로 검증
        for(int i = 0; i < N; i++){
            String s = "";
            for(int j = 0; j < N; j++){
                // 비어있으면 넘어가고
                if(graph[j][i] == 'X'){
                    continue;
                }
                // S 또는 T 또는 B는 넣고
                s = s + graph[j][i];
            }
            // 이렇게 붙어있으면 안돼
            if(s.contains("ST") || s.contains("TS")){
                return false;
            }
        }
        // 여기까지 다 통과하면 감시 피한거야
        return true;
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

//        @Override
//        public String toString() {
//            return "Point{" +
//                    "x=" + x +
//                    ", y=" + y +
//                    '}';
//        }
    }
}