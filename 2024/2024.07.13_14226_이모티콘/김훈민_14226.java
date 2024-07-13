import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 김훈민_14226 {
    static int S;
    static int[][] dp;
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        S = Integer.parseInt(br.readLine());
        dp = new int[2001][2001];
        queue.add(new Point(1, 0, 0));
        dp[1][0] = 0;
        bfs();

    }

    static void bfs(){
        while (!queue.isEmpty()){
            Point p = queue.poll();
            if(p.num == S){
                System.out.println(p.time);
                return;
            }
            // 1 빼기
            if(p.num > 2 && dp[p.num - 1][p.clipBoard] == 0){
                queue.add(new Point(p.num - 1, p.clipBoard, p.time + 1));
                dp[p.num - 1][p.clipBoard] = p.time + 1;
            }
            // 클립보드에 저장
            if(p.clipBoard != p.num) {
                queue.add(new Point(p.num, p.num, p.time + 1));
            }
            // 클립보드에 있는거 붙이기
            if(p.clipBoard != 0 && dp[p.num + p.clipBoard][p.clipBoard] == 0 && p.num + p.clipBoard <= S){
                queue.add(new Point(p.num + p.clipBoard, p.clipBoard, p.time + 1));
                dp[p.num + p.clipBoard][p.clipBoard] = p.time + 1;
            }
        }
    }

    static class Point{
        int num;
        int clipBoard;
        int time;
        Point(int num, int clipBoard, int time){
            this.num = num;
            this.clipBoard = clipBoard;
            this.time = time;
        }
    }
}
