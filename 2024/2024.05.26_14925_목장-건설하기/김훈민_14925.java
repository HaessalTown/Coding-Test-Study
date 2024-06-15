import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김훈민_14925 {
	public static void main(String[] args) throws Exception {
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] vector = {{1, 0}, {0, 1}, {1, 1}};
		int[][] graph = new int[M][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] != 0) graph[i][j] = -1;
				else if(graph[i][j] == 0) graph[i][j] = 1;
			}
		}
		
//		debug
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < M; i++) {
//			for(int j = 0; j < N; j++) {
//				sb.append(graph[i][j]).append(" ");
//			}
//			sb.append("\n");
//		}
//		System.out.println(sb);
		
		for(int i = 1; i < M; i++) {
			for(int j = 1; j < N; j++) {
				if(graph[i][j] == -1) continue;
				if(graph[i][j - 1] == -1 || graph[i - 1][j] == -1 || graph[i - 1][j - 1] == -1) {
					graph[i][j] = 1;
					continue;
				}
				int temp = graph[i - 1][j - 1];
				temp = Math.min(temp, graph[i][j - 1]);
				temp = Math.min(temp, graph[i - 1][j]);
				graph[i][j] = temp + 1;
			}
		}
		
//		debug
//		sb = new StringBuilder();
//		for(int i = 0; i < M; i++) {
//			for(int j = 0; j < N; j++) {
//				sb.append(graph[i][j]).append(" ");
//			}
//			sb.append("\n");
//		}
//		System.out.println(sb);
		
	
		
		int result = 0;
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(result < graph[i][j]) {
					result = graph[i][j];
				}
			}
		}
		System.out.println(result);
		
	}
	

}