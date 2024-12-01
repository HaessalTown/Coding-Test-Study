import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김훈민_3078 {
    static int N, K;
    static int[] nameLenDP = new int[21];
    static int[] nameLen;
    static long result;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nameLen = new int[N];
        for(int i = 0; i < N; i++){
            nameLen[i] = br.readLine().length();
            if(i < K){
                nameLenDP[nameLen[i]]++;
            }
            else if(i == K){
                nameLenDP[nameLen[i]]++;
                nameLenDP[nameLen[0]]--;
                result += nameLenDP[nameLen[0]];
            }
        }

        for(int i = 1; i < N; i++){
            if(i + K < N) nameLenDP[nameLen[i + K]]++;
            nameLenDP[nameLen[i]]--;
            result += nameLenDP[nameLen[i]];
        }

        System.out.println(result);
    }
}
