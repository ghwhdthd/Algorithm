import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N; // 사탕 종류 (1 ~ 5000)
    private static int M; // 돈의 양 (0.01 ~ 100.00)
    private static int[] C; // 칼로리 (1 ~ 5000)
    private static int[] P; // 가격 (0.01 ~ 100.00)
    private static int[] dp;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        while (true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
            double n = 1.0;
            int x = 10;
            if(N == 0 && M == 0.00) break;
            pro();
        }
    }
    private static void pro() throws IOException{
        C = new int[N+1];
        P = new int[N+1];
        for (int i = 1; i <=N; i++) {
            st = new StringTokenizer(br.readLine());
            C[i] = Integer.parseInt(st.nextToken());
            P[i] = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
        }
        knapsack();
    }
    private static void knapsack(){
        //dp는 칼로리
        dp = new int[M+1];
        //i번째 사탕까지 고려
        for (int i = 1; i <= N; i++) {
            // j는 남은 돈
            for (int j = 1; j <= M; j++) {
                if(j - P[i] >= 0){
                    //사탕을 담을 수 있는 경우
                    // 담지 않거나, 담거나 중 최대 칼로리를 저장
                    dp[j] = Math.max(dp[j], C[i] + dp[j - P[i]]);
                }
            }
        }
        System.out.println(dp[M]);
    }
}