import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김훈민_12869 {
    static int result = Integer.MAX_VALUE;
    static int N;
    static int[] scvHealth;
    static int[][][] dp;
    static int[][] choice =
            {{1, 3, 9}, {1, 9, 3},
            {3, 1, 9}, {3, 9, 1},
            {9, 3, 1}, {9, 1, 3}};
    public static void main(String[] args) throws Exception {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        scvHealth = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scvHealth[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[scvHealth[0] + 1][scvHealth[1] + 1][scvHealth[2] + 1];
        for(int i = 0; i < scvHealth[0] + 1; i++) {
            for(int j = 0; j < scvHealth[1] + 1; j++) {
                for(int k = 0; k < scvHealth[2] + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        result = Math.min(result, dfs(scvHealth[0], scvHealth[1], scvHealth[2]));
        System.out.println(result);
    }

    static int dfs(int scvHP1, int scvHP2, int scvHP3) {
        if(scvHP1 < 0 ) scvHP1 = 0;
        if(scvHP2 < 0 ) scvHP2 = 0;
        if(scvHP3 < 0 ) scvHP3 = 0;

        if(scvHP1 == 0 && scvHP2 == 0 && scvHP3 == 0) return 0;

        if(dp[scvHP1][scvHP2][scvHP3] != -1) return dp[scvHP1][scvHP2][scvHP3];

        int temp = Integer.MAX_VALUE;

        for(int i = 0; i < 6; i++){
            temp = Math.min(temp, dfs(scvHP1 - choice[i][0], scvHP2 - choice[i][1], scvHP3 - choice[i][2]) + 1);
        }

        dp[scvHP1][scvHP2][scvHP3] = temp;

        return temp;
    }
}
