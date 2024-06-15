import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김훈민_19539 {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        int N = Integer.parseInt(br.readLine());
        int[] apple_trees = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            apple_trees[i] = Integer.parseInt(st.nextToken());
        }
        int div_2 = 0;
        int mod_2 = 0;
        for(int i = 0 ; i < N; i++){
            div_2 += apple_trees[i] / 2;
            mod_2 += apple_trees[i] % 2;
        }
        if(div_2 < mod_2){
            System.out.println("NO");
            return;
        }
        while(div_2 != mod_2){
            div_2--;
            mod_2 += 2;
            if(div_2 < mod_2){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}