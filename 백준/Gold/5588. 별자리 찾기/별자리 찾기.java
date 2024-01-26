import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,M; // M == hope, N == real
    private static int[][] hope;
    private static int[][] real;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        hope = new int[M][2];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            hope[i][0] = Integer.parseInt(st.nextToken());
            hope[i][1] = Integer.parseInt(st.nextToken());
        }

        N = Integer.parseInt(br.readLine());
        real = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            real[i][0] = Integer.parseInt(st.nextToken());
            real[i][1] = Integer.parseInt(st.nextToken());
        }
        //input 끝

        for (int i = 0; i < real.length; i++) {
            int x = real[i][0] - hope[0][0];
            int y = real[i][1] - hope[0][1];
            if (check(x, y)) {
                System.out.println(x + " " + y);
                break;
            }
        }


    }
    private static boolean check(int x,int y){
        boolean isOk = true;
        visit = new boolean[N];
        int count =0;
        for (int i = 0; i < hope.length; i++) {
           int tmpx = hope[i][0] + x;
           int tmpy = hope[i][1] + y;
            for (int j = 0; j < real.length; j++) {
                if(visit[j]) continue;
                if(real[j][0] == tmpx && real[j][1] == tmpy){
                    visit[j] = true;
                    count++;
                }
            }
        }
        if(count != M) isOk = false;

        return isOk;
    }

}