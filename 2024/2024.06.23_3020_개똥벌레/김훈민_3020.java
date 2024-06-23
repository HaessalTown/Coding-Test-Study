import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 김훈민_3020 {
	static int N, H;
	static int[] up_arr, down_arr;
	// false => down , true => up
	static boolean up_down;
	public static void main(String[] args) throws IOException {
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		up_arr = new int[H];
		down_arr = new int[H];
		for(int i = 0; i < N; i++) {
			if(up_down) {
				up_arr[H - Integer.parseInt(br.readLine())]++;
				up_down = false;
			}
			else {
				down_arr[Integer.parseInt(br.readLine()) - 1]++;
				up_down = true;
			}
		}
		// 누적합
		for(int i = 1; i < H; i++) {
			up_arr[i] = up_arr[i] + up_arr[i - 1];
			down_arr[H - i - 1] = down_arr[H - i] + down_arr[H - i - 1];
		}
		// 두 배열을 더하기
		for(int i = 0; i < H; i++) {
			down_arr[i] = down_arr[i] + up_arr[i];
		}
		int result = 0;
		int temp = down_arr[0];
		result++;
		for(int i = 1; i < H; i++) {
			if(down_arr[i] < temp) {
				result = 0;
				temp = down_arr[i];
				result++;
			}
			else if(down_arr[i] == temp) {
				result++;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(temp).append(" ").append(result);
		System.out.println(sb);
	}
}
