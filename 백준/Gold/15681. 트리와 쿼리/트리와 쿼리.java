import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, R, Q;
    private static List<List<Integer>> li;
    private static int[] dp;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 수
        R = Integer.parseInt(st.nextToken()); // 루트의 번호
        Q = Integer.parseInt(st.nextToken()); // 쿼리의 수
        li = new ArrayList<>();
        dp = new int[N+1];
        visit = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            li.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //무방향 트리 == 양방향
            li.get(a).add(b);
            li.get(b).add(a);
        }
        dfs(R);
        for (int i = 0; i < Q; i++) {
            int node = Integer.parseInt(br.readLine());
            System.out.println(dp[node] + 1);
        }
    }
    private static void dfs(int parent){
        visit[parent] = true;
        List<Integer> childList = li.get(parent);
        for(int child : childList){
            if(visit[child]) continue;
            dfs(child);
            dp[parent] += dp[child] + 1;
        }
    }
}