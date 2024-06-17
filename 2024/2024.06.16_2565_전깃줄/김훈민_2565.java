import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Point[] electricLines;

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        electricLines = new Point[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            electricLines[i] = new Point(from, to);
        }
        Arrays.sort(electricLines);

        int[] lis = new int[N];
        int length = 0;

        for (int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(lis, 0, length, electricLines[i].to);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            lis[pos] = electricLines[i].to;
            if (pos == length) {
                length++;
            }
        }
        int result = N - length;
        System.out.println(result);
    }

    static class Point implements Comparable<Point> {
        int from;
        int to;

        Point(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Point p) {
            return Integer.compare(this.from, p.from);
        }
    }
}
