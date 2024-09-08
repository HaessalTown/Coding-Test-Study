import java.util.*;
import java.io.*;


public class 김훈민_최소_차_분할 {
    static int N;
    static int[] nums;
    static boolean[] canSplit;
    public static void main(String[] args) throws Exception {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
        }
        canSplit = new boolean[sum + 1];
        canSplit[0] = true;

        for(int num : nums){
            for(int j = sum; j >= num; j--){
                canSplit[j] = canSplit[j] || canSplit[j - num];
            }
        }

        int mid = sum / 2;

        for(int i = mid; i >= 0; i--){
            if(canSplit[i]){
                System.out.println(sum - (i * 2));
                break;
            }
        }
    }
}
