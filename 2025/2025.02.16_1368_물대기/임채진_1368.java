import java.util.*;
import java.io.*;

public class P1368_물대기 {
    static int n;
    static ArrayList<Pair> edges = new ArrayList<>();
    static int[] parent;

    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    public static void main(String[] argst) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            int w = Integer.parseInt(br.readLine());

            edges.add(new Pair(0, i, w));
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    edges.add(new Pair(i, j, Integer.parseInt(st.nextToken())));
                } else {
                    Integer.parseInt(st.nextToken()); // 입력은 받지만 버림
                }
            }
        }

        Collections.sort(edges);

        int sum = 0;
        for (Pair node : edges) {
            if (find(node.x) != find(node.y)) {
                union(node.x, node.y);
                sum += node.dist;
            }
        }

        System.out.println(sum);
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }
}
