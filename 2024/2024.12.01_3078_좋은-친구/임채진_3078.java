import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();

        int size = 0;
        long ans = 0;
        for (int i = 0; i < k - 1; i++) {
            String student = arr[i];

            int length = student.length();

            if (map.containsKey(length))
                ans += map.get(length);

            list.add(student);
            if (map.containsKey(length)) {
                map.put(length, map.get(length) + 1);
            } else {
                map.put(length, 1);
            }
            size += 1;
        }

        for (int i = k - 1; i < n; i++) {
            String student = arr[i];
            int length = student.length();

            if (map.containsKey(length))
                ans += map.get(length);

            list.add(student);
            if (map.containsKey(length)) {
                map.put(length, map.get(length) + 1);
            } else {
                map.put(length, 1);
            }
            size += 1;

            if (size > k) {
                map.put(list.get(0).length(), map.get(list.get(0).length()) - 1);
                list.remove(0);
            }
        }

        System.out.println(ans);
    }
}