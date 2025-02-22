import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김훈민_10986 {
    static int N, M;
    static long[] sumNum;
    static long[] modulerNum;
    static long[] prefixSort;
    static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumNum = new long[N];
        modulerNum = new long[N];
        prefixSort = new long[M+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sumNum[i] = Long.parseLong(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            if(i >= 1){
                sumNum[i] = sumNum[i-1] + sumNum[i];
            }
            modulerNum[i] = sumNum[i] % M;
            prefixSort[(int) modulerNum[i]]++;
            if(modulerNum[i] == 0) result++;
        }
        for(int i = 0; i < M; i++){
            if(prefixSort[i] > 1){
                result += prefixSort[i] * (prefixSort[i]-1) / 2;
            }
        }
        System.out.println(result);
    }
}
