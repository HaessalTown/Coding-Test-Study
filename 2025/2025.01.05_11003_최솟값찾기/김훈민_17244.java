package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 김훈민_17244 {
    static int N, L;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new ArrayDeque<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        sliding();
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void sliding(){
        for(int i = 0; i < N; i++){
            while(!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
            if(deque.peek() <= i - L){
                deque.poll();
            }
            sb.append(nums[deque.peekFirst()]).append(" ");
        }
    }
}
