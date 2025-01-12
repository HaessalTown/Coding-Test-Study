import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김훈민_8980.java {
    static int N, C, M;
    static Box[] boxes;
    static int[] capacities;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        boxes = new Box[M];
        capacities = new int[N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            boxes[i] = new Box(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(boxes);
        for(Box box : boxes){
            int maxRide = box.weight;
            for(int i = box.fromIdx; i < box.toIdx; i++){
                int remainRide = C - capacities[i];
                maxRide = Math.min(maxRide, remainRide);
            }

            int realRide = Math.min(maxRide, box.weight);

            for(int i = box.fromIdx; i < box.toIdx; i++){
                capacities[i] += realRide;
            }
            result += realRide;
        }
        System.out.println(result);
    }

    static class Box implements Comparable<Box>{
        int fromIdx;
        int toIdx;
        int weight;

        Box(int fromIdx, int toIdx, int weight){
            this.fromIdx = fromIdx;
            this.toIdx = toIdx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Box o) {
            if(this.toIdx == o.toIdx){
                return o.fromIdx - this.fromIdx;
            }
            return this.toIdx - o.toIdx;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "fromIdx=" + fromIdx +
                    ", toIdx=" + toIdx +
                    ", weight=" + weight +
                    '}';
        }
    }
}
