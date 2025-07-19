import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김훈민_1561 {
    static int N, M;
    static long[] waitTimes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        waitTimes = new long[M];

        st = new StringTokenizer(br.readLine());
        long maxWaitTime = Long.MIN_VALUE;
        for(int i = 0; i < M; i++){
            waitTimes[i] = Long.parseLong(st.nextToken());
            maxWaitTime = Math.max(maxWaitTime, waitTimes[i]);
        }

        if(N <= M){
            System.out.println(N);
            return;
        }

        long start = 0;
        long end = maxWaitTime * N;
        long tempAns = end;
        // 예상 시간으로 나눴을 때, 각 놀이기구 앞에 줄은 몇명이 서있는가?
        while(start <= end){
            long mid = (start + end) / 2;
            long count = M;

            for(long t : waitTimes){
                count += mid / t;
            }

            if(count >= N){
                tempAns = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // 이미 탄 사람 계산
        long temp = M;
        for(long t : waitTimes){
            temp += (tempAns - 1) / t;
        }
        long remain = N - temp;

        for(int i = 0; i < M; i++){
            // 나머지 0이면 딱맞는거고
            if(tempAns % waitTimes[i] == 0){
                if(--remain == 0){
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }
}
