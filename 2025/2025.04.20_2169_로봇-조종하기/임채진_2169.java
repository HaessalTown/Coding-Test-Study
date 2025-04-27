import java.util.*;
import java.io.*;

public class 임채진_2169 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        for (int j = 1; j < m; j++) { // right
            dp[0][j] = dp[0][j-1] + arr[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] ldp = new int[m];
            int[] rdp = new int[m];

            // 왼 -> 오
            ldp[0] = dp[i-1][0] + arr[i][0];
            for (int j = 1; j < m; j++) {
                ldp[j] = Math.max(ldp[j-1], dp[i-1][j]) + arr[i][j];
            }

            // 오 -> 왼
            rdp[m-1] = dp[i-1][m-1] + arr[i][m-1];
            for (int j = m - 2; j >= 0; j--) {
                rdp[j] = Math.max(rdp[j+1], dp[i-1][j]) + arr[i][j];
            }

            for (int j = 0; j < m; j++) { // 왼, 오 비교
                dp[i][j] = Math.max(ldp[j], rdp[j]);
            }
        }

        /*for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }*/

        System.out.println(dp[n - 1][m - 1]);
    }
}