import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// dfs 돌려서 그냥 자식이 없는 노드면 리턴하면서 1주고
// 1 받은넘은 개수 1 올려주고 1받은 넘은 얼리어답터가 되고
// 얼리어답터는 리턴하면서 0 주고 0 받은놈은 밑이 얼리어답터니까 얼리어답터 될 필요가 없지않나?
public class 김훈민_2533 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    static int N;
    static int result;

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        for(int i = 0; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++){
            String[] input = br.readLine().split(" ");
            graph.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
            graph.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
        }
        visited[1] = true;
        dfs(1);
        System.out.println(result);
    }

    static boolean dfs(int x){
        boolean flag = false;
        for(Integer i : graph.get(x)){
            // 방문하지 않았으면 방문한다
            if(!visited[i]){
                visited[i] = true;
                if(!dfs(i)) {
                    flag = true;
                }
            }
        }
        // 주장 : 나는 얼리어답터야
        if(flag){
            result++;
            return true;
        }
        // 주장 : 나는 자식 노드야, 얼리어답터가 아니야
        return false;
    }
    
}