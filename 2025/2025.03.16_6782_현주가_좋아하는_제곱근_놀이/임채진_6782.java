import java.util.*;
import java.io.*;
 
public class Solution {
    static StringBuilder sb = new StringBuilder();
    static Stack<Pair> stack = new Stack<>();
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            long n = Long.parseLong(br.readLine());
            long cnt = 0;
             
            stack.push(new Pair(n, 0));
            while (!stack.isEmpty()) {
                Pair current = stack.pop();
                 
                if (current.num == 2) {
                    cnt = current.cnt;
                    break;
                }
                 
                if (Math.sqrt(current.num) != (long)Math.sqrt(current.num)) { // sqrt한 값이 정수이면
                    long temp = ((long)Math.sqrt(current.num) + 1) * ((long)Math.sqrt(current.num) + 1);
                     
                    stack.add(new Pair(temp, current.cnt + temp - current.num));
                } else {
                    stack.add(new Pair((long)Math.sqrt(current.num), current.cnt + 1));
                }
            }
            stack.clear();
             
            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
         
    }
     
    static class Pair {
        long num;
        long cnt;
         
        public Pair(long num, long cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
     
}