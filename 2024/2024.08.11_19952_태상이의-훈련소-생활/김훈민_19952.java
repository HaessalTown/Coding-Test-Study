import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김훈민_19952 {
    static int N, M;
    static int[] ground;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ground = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            ground[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dp[from] += weight;
            if(to < N) dp[to] -= weight;
        }
        for(int i = 1; i < N; i++){
            dp[i] = dp[i - 1] + dp[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            ground[i] = ground[i] + dp[i];
            sb.append(ground[i]).append(" ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
