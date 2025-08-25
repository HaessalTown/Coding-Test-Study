import java.util.*;
import java.io.*;

public class 임채진_2042 {
    static int n, m, k;
    static int base, size;
    static long[] arr;
    static long[] tree;

    static void makeTree() {

        // 리프 노드에 배열 집어넣기
        for (int i = 0; i < n; i++) {
            tree[i + base] = arr[i];
        }

        // 트리 완성
        for (int i = base - 1; i > 0; i--) { // 역순으로 진행
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    static void update(int idx, long value) {
        arr[idx] = value;
        tree[base + idx] = value;

        int node = (base + idx) / 2;
        while (node >= 1) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }

    static long query(int idx, int s, int e, int ts, int te) {
        // 1) 겹치지 않음
        if (e < ts || te < s) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        long a = query(idx * 2, s, mid, ts, te);
        long b = query(idx * 2 + 1, mid + 1, e, ts, te);

        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        base = 1;
        while (base < n) base <<= 1;
        size = base << 1;

        tree = new long[size];
        arr = new long[base];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        makeTree();

        // 쿼리
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                // 배열에서 b번째 idx를 c로 바꿈
                update((int) b - 1, c);
            } else if (a == 2) { // 구간합 구하기
                long sum = query(1, 0, base - 1, (int) b - 1, (int) c - 1);
                System.out.println(sum);
            }
        }
    }
}
