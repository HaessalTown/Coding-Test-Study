import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김훈민_2169 {
    static int N, M;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] dpPrev = new int[M];
        int[] dpCurr = new int[M];
        int[] left = new int[M];
        int[] right = new int[M];
        dpPrev[0] = graph[0][0];
        for(int j = 1; j < M; j++)
            dpPrev[j] = dpPrev[j-1] + graph[0][j];

        for(int i = 1; i < N; i++) {

            left[0] = dpPrev[0] + graph[i][0];
            for(int j = 1; j < M; j++)
                left[j] = Math.max(left[j-1], dpPrev[j]) + graph[i][j];

            right[M-1] = dpPrev[M-1] + graph[i][M-1];
            for(int j = M-2; j >= 0; j--)
                right[j] = Math.max(right[j+1], dpPrev[j]) + graph[i][j];

            for(int j = 0; j < M; j++)
                dpCurr[j] = Math.max(left[j], right[j]);

            System.arraycopy(dpCurr, 0, dpPrev, 0, M);
        }
        System.out.println(dpPrev[M-1]);
    }
}
