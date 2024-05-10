import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] dp;
    private static List<List<Integer>>li;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2]; // dp[1][1] -> 1번 노드가 얼리어답터인 경우
                              // dp[1][0] -> 1번 노드가 얼리어답터가 아닌경우
        li = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            li.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            li.get(from).add(to);
            li.get(to).add(from);
        }
        visit = new boolean[N+1];
        dfs(1);
        System.out.println(Math.min(dp[1][0] , dp[1][1]));
    }
    private static void dfs(int parent){
        //케이스 2개로 나뉨
        // 1) 부모가 얼리어답터인 경우 -> 자식은 얼리어답터 이거나 아니거나
        // 2) 부모가 얼리어답터가 아닌 경우 -> 자식은 얼리어답터여야함
        dp[parent][0] = 0;
        dp[parent][1] = 1;
        visit[parent] = true;
        List<Integer> childList = li.get(parent);
        for(int child : childList){
            if(visit[child]) continue;
            dfs(child);
            dp[parent][0] += dp[child][1];
            dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
        }

    }
}