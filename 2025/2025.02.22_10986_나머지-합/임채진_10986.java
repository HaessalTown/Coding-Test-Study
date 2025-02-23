import java.util.*;
import java.io.*;

/**
 * Solved_1225_암호생성기
 * 난이도 7/10
 * 누적합
 * 608ms
 *
 * 누적합을 구한 후 M으로 나누어준다.
 * mod 연산의 분배법칙에 의해 각각의 원소에서의 나머지를 계수정렬식으로 정리해서 같은 나머지인 경우 그 개수만큼 조합 연산(수학적 연산)을 해주어 답을 구하였다.
 *
 */
public class P10986_나머지_합 {
    static HashMap<Long, Long> map = new HashMap<>(); // 나머지 - 개수 pair식으로 HashMap에 데이터를 집어넣음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 구하기
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + arr[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            prefixSum[i] %= m;

            // hashmap에 값을 집어넣는다.
            if (!map.containsKey(prefixSum[i])) {
                map.put(prefixSum[i], 1L);
            } else {
                map.put(prefixSum[i], map.get(prefixSum[i]) + 1);
            }
        }

        long ans = 0;
        for (long key : map.keySet()) {
            if (key == 0) {
                ans += map.get(key);
            }

            ans += map.get(key) * (map.get(key) - 1) / 2; // 조합 연산
        }

        System.out.println(ans);
    }
}