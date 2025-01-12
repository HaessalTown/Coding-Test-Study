import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());

        int ans = 0;
        Pair[] arr = new Pair[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            arr[i] = new Pair(start, end, value);
        }

        Arrays.sort(arr);

        int[] weight = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            weight[i] = C;
        }

        for (int i = 0; i < M; i++) {
            Pair current = arr[i];

            int posNum = Integer.MAX_VALUE;

            for (int j = current.x; j < current.y; j++) {
                posNum = Math.min(posNum, weight[j]);
            }

            if (posNum >= current.value) { // 택배 전체를 보낼 수 있음
                for (int j = current.x; j < current.y; j++) {
                    weight[j] -= current.value;
                }
                ans += current.value;
            } else { // 택배 일부만 보냄
                for (int j = current.x; j < current.y; j++) {
                    weight[j] -= posNum;
                }
                ans += posNum;
            }
        }

        System.out.println(ans);
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int value;

        public Pair(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Pair p) {
            if (y == p.y) {
                return x - p.x;
            }

            return y - p.y;
        }

        @Override
        public String toString() {
            return x + " " + value;
        }
    }
}
