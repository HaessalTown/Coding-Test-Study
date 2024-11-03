import java.util.*;
import java.io.*;

public class P14466_소가_길을_건너간_이유6 {
    public static int n;
    public static int[][] grid;
    public static boolean[][] visited;
    public static HashSet<Pair>[][] temp;
    public static ArrayList<Pair> cows;
    public static Queue<Pair> queue = new LinkedList<>();

    static class Pair implements Comparable<Pair>{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair p) {
            if (this.x != p.x) {
                return this.x - p.x;
            }
            return this.y - p.y;
        }
    }

    public static void bfs() {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            int ax = current.x;
            int ay = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = ax + dx[i];
                int ny = ay + dy[i];

                if (!(0 <= nx && nx < n && 0 <= ny && ny < n)) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    boolean flag = false;
                    for (Pair t : temp[ax][ay]) {
                        if (t.x == nx && t.y == ny) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) { continue; }

                    visited[nx][ny] = true;
                    queue.add(new Pair(nx, ny));
                }
            }
        }
    }

    public static void initialize() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        visited = new boolean[n][n];
        temp = new HashSet[n][n];

        cows = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = new HashSet<>();
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            temp[r1 - 1][c1 - 1].add(new Pair(r2 - 1, c2 - 1));
            temp[r2 - 1][c2 - 1].add(new Pair(r1 - 1, c1 - 1));
        }

        /*for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(temp[i]));
        }*/

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cows.add(new Pair(a - 1, b - 1));
            grid[a - 1][b - 1] = 1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    visited[i][j] = true;
                    queue.add(new Pair(i, j));
                    bfs();

                    /*for (int l = 0; l < n; l++) {
                        System.out.println(Arrays.toString(visited[l]));
                    }*/

                    for (int l = 0; l < cows.size(); l++) {
                        Pair cow = cows.get(l);
                        if (cow.x == i && cow.y == j) continue;

                        if (!visited[cow.x][cow.y]) {
                            ans += 1;
                        }
                    }

                    initialize();
                }
            }
        }

        System.out.println(ans / 2);
    }
}