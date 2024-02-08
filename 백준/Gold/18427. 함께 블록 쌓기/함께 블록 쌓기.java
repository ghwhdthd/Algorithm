import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,M,H;
    private static int[][] bHeight; //학생이 가지고 있는 블럭높이
    private static int[][] dp; // i번째 학생까지 고려했을때 목표높이 h가 될 수 있는 경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());// 목표 높이
        bHeight = new int[N+1][M+1];
        dp = new int[N+1][H+1]; // 경우의 수 저장 : N은 학생 번호, H는 목표 높이
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                if(!st.hasMoreTokens()) break;
                bHeight[i][j] =Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <=N ; i++) {
            for (int h = 0; h <= H; h++) {
                //목표 높이가 0 이면 아무것도 선택안하는 경우 1가지밖에 없음
                if(h ==0) {
                    dp[i][h] = 1;
                    continue;
                }
                //0번째 학생인 경우 continue
                if(i==0) continue;
                for (int j = 1; j < bHeight[i].length; j++) {
                    int now = bHeight[i][j];
                    //현재 블럭 > 목표 높이인 경우 continue
                    if(h-now <0 )continue;
                    //현재 블럭이 0인 경우는 위에서 입력 받을 때 없는 값을 0으로 받았기 때문에 continue
                    if(now ==0)continue;
                    //i번째 학생까지 고려했을때 목표 높이 h까지 도달할 수 있는 경우의 수 넣기 (i번째 학생의 블럭 선택한 경우)
                    dp[i][h] += dp[i-1][h-now];
                    dp[i][h] %=10007;
                }
                //목표높이가 h 일때 i-1번째 학생까지 고려했을 때 나왔던 경우의 수 추가하기 (i번째 학생의 블럭 하나도 선택하지 않은경우)
                dp[i][h] += dp[i-1][h];
                dp[i][h] %=10007;
            }
        }
        System.out.println(dp[N][H]);
    }
}