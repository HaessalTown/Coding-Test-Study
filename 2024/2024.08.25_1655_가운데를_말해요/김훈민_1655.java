import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 김훈민_1655 {
    static int[] countNum = new int[20001];
    static int N;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int nowNumber = Integer.parseInt(br.readLine());
            countNum[nowNumber + 10000]++;
            result.append(findMid(i + 1)).append("\n");
        }
        System.out.print(result);
    }

    static int findMid(int n){
        int findSize = (n + 1) / 2;
        int count = 0;
        for(int i = 0; i < 20001; i++){
            count += countNum[i];
            if(count >= findSize){
                return (i - 10000);
            }
        }
        return 30000;
    }
}
