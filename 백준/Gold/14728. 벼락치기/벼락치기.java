import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,T;
    private static int[][] problem;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        problem = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            problem[i][0] = Integer.parseInt(st.nextToken());
            problem[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N+1][T+1];

        for (int i = 1; i < problem.length; i++) {
            int K = problem[i][0]; // 공부하는데 들어가는 시간
            int S = problem[i][1]; // 얻을 수 있는 점수
            //남은 시간이 j일 때 i 번 문제 선택할지 말지
            for (int j = 1; j <= T; j++) {
                if(K > j){
                    dp[i][j] = dp[i - 1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],
                        S + dp[i-1][j-K]);
                }
            }
        }
        System.out.println(dp[N][T]);

    }
}