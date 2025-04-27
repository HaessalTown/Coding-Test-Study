import java.util.*;
import java.io.*;

public class 임채진_1414 {
    static int[] parents;

    static int find(int x) {
        if (x == parents[x]) return x;

        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa < pb) {
            parents[pb] = pa;
        } else {
            parents[pa] = pb;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] table = new int[n][n];

        // 초기화
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int overall = 0;
        ArrayList<Edge> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char x = line.charAt(j);

                if (x == '0') {
                    table[i][j] = 0;
                    continue;
                }

                if ('a' <= x && x <= 'z') {
                    list.add(new Edge(i, j, x - 'a' + 1));
                    overall += x - 'a' + 1;

                    table[i][j] = x - 'a' + 1;
                } else if ('A' <= x && x <= 'Z') {
                    list.add(new Edge(i, j, x - 'A' + 27));
                    overall += x - 'A' + 27;

                    table[i][j] = x - 'A' + 27;
                }
            }
        }

        /*for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(table[i]));
        }*/

        Collections.sort(list);

        int cost = 0;
        for (Edge e : list) {
            int x = e.x;
            int y = e.y;

            if (find(x) != find(y)) {
                cost += e.dist;
                union(x, y);
            }
        }

        boolean isConnected = true;

        int temp = -1;
        for (int i = 0; i < n; i++) {
            if (temp == -1) {
                temp = find(i);
            } else {
                if (find(i) != temp) {
                    isConnected = false;
                    break;
                }
            }
        }

        if (isConnected) {
            System.out.println(overall - cost);
        } else {
            System.out.println(-1);
        }

    }

    static class Edge implements Comparable<Edge> {
        int x;
        int y;
        int dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge other) {
            return this.dist - other.dist;
        }
    }
}
