import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int V,E;
    private static int[][] dp;
    private static int INF = 4_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dp = new int[V+1][V+1];
        for (int i = 0; i <=V; i++) {
            for (int j = 0; j <=V; j++) {
                dp[i][j] =INF;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dp[from][to] = Math.min(weight , dp[from][to]);
        }
        for (int i = 1; i <=V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        int res = INF;
        for (int i = 1; i <= V; i++) {
            res = Math.min(dp[i][i], res);
        }
        if (res == INF) {
            System.out.println(-1);
        }else{
            System.out.println(res);
        }

    }
}