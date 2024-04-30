import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] cost;
    private static int[][] dp;
    private static int RED = 0;
    private static int GREEN = 1;
    private static int BLUE = 2;
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N+1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cost[i][0] = r;
            cost[i][1] = g;
            cost[i][2] = b;

        }

        dp = new int[N+1][3];
        for (int rgb = 0; rgb < 3; rgb++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < 3; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            painting(1, rgb, rgb);
            for (int i = 0; i < 3; i++) {
                if(min > dp[N][i]) min = dp[N][i];
            }
        }
        System.out.println(min);
    }
    private static void painting(int depth, int rgb, int start){
        if(depth > N) return;
        if(depth == N && start == rgb){
            return;
        }
        if(rgb == RED){
            //red
            if(dp[depth][rgb] > cost[depth][rgb] + Math.min(dp[depth-1][GREEN], dp[depth-1][BLUE])){
                dp[depth][rgb] = cost[depth][rgb] + Math.min(dp[depth-1][GREEN], dp[depth-1][BLUE]);
                painting(depth + 1, GREEN, start);
                painting(depth + 1, BLUE, start);
            }

        }else if(rgb == GREEN){
            //green
            if(dp[depth][rgb] > cost[depth][rgb] + Math.min(dp[depth-1][RED], dp[depth-1][BLUE])){
                dp[depth][rgb] = cost[depth][rgb] + Math.min(dp[depth-1][RED], dp[depth-1][BLUE]);
                painting(depth + 1, RED, start);
                painting(depth + 1, BLUE, start);
            }
        }else{
            //blue
            if(dp[depth][rgb] > cost[depth][rgb] + Math.min(dp[depth-1][RED], dp[depth-1][GREEN])){
                dp[depth][rgb] = cost[depth][rgb] + Math.min(dp[depth-1][RED], dp[depth-1][GREEN]);
                painting(depth + 1, RED, start);
                painting(depth + 1, GREEN, start);
            }

        }

    }
}