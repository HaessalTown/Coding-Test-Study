import java.util.*;
import java.io.*;

public class Main {
    static int n, m, sx, sy, itemCnt, ans = Integer.MAX_VALUE;
    static char[][] grid;
    static boolean[][][] visited;
    static Queue<int[]> queue = new LinkedList<>();

    static void bfs() {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                if (grid[nx][ny] == '.') {
                    if (!visited[nx][ny][current[3]]) {
                        visited[nx][ny][current[3]] = true;
                        queue.add(new int[]{nx, ny, current[2] + 1, current[3]});
                    }
                } else if (grid[nx][ny] == 'E') {
                    if (current[3] == (1 << itemCnt) - 1) {
                        ans = Math.min(ans, current[2] + 1);
                    }
                } else if (grid[nx][ny] != '#') {
                    if (!visited[nx][ny][current[3] | (1 << (grid[nx][ny] - '0'))]) {
                        visited[nx][ny][current[3] | (1 << (grid[nx][ny] - '0'))] = true;
                        queue.add(new int[]{nx, ny, current[2] + 1, current[3] | (1 << (grid[nx][ny] - '0'))});
                    }
                }
            }
        }
    }

    static void print() {
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[m][];
        // grid 배열에 저장
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
        }

        itemCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;

                    grid[i][j] = '.';
                } else if (grid[i][j] == 'X') {
                    grid[i][j] = (char)(itemCnt + '0');
                    itemCnt++;
                }
            }
        }

        visited = new boolean[m][n][1 << itemCnt];
        queue.add(new int[]{sx, sy, 0, 0});

        bfs();

        System.out.println(ans);
    }
}
