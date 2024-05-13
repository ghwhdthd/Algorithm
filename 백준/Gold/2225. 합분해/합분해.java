import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import org.w3c.dom.ls.LSOutput;

public class Main {
    private static int N, K;
    private static int[][]dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if(j == 1) dp[i][j] = 1;
                if(i == 1) dp[i][j] = j;
            }
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j]= (dp[i-1][j] + dp[i][j-1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[N][K]);
    }

}