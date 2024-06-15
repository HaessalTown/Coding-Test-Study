import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 김훈민_1715 {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pqueue = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            pqueue.add(Long.parseLong(br.readLine()));
        }
        // 비교 시작
        long result = 0;
        while(pqueue.size() >= 2){
            long num1 = pqueue.poll();
            long num2 = pqueue.poll();
            result += (num1 + num2);
            pqueue.add(num1 + num2);
        }
        // 여기오면 1개 남은것
        System.out.println(result);
    }
}