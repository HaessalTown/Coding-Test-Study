import java.util.*;
import java.io.*;

public class 김훈민_원소의-합이-0 {
    static int N;
    static int[] A, B, C, D;
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception{
                InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }
        // input end

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int temp = A[i] + B[j];
                if(!map.containsKey(temp)) map.put(temp, 1);
                else map.put(temp, map.get(temp)+1);
            }
        }

        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int temp = (C[i] + D[j]) * -1;
                if(map.containsKey(temp)){
                    int cnt = map.get(temp);
                    result += cnt;
                }
            }
        }
        System.out.println(result);
    }
}
