import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int T,N,M;
    private static int[] coins;
    private static int[] dp;
    private static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            pro();
            System.out.println(dp[M]);
        }
    }
    private static void pro() throws IOException{
        N = Integer.parseInt(br.readLine());
        coins = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        dp = new int[M+1];

        for (int i = 1; i <= N; i++) {
            int coin = coins[i];
            for (int j = 1; j <= M; j++) {
                if(j - coin < 0) continue;
                if(j - coin == 0){
                    dp[j] = dp[j] + 1;
                    continue;
                }
                dp[j] = dp[j] + dp[j-coin];
            }
        }
    }
}