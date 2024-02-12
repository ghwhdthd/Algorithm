import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int[]queen;
    private static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queen = new int[N+1];// 퀸이 있는 위치를 의미 queen[1] =2 : 1행2열에 퀸이 위치하고 있다.
        for (int c = 1; c <=N ; c++) {

            setNqueen(1, c);
        }

        System.out.println(res);
    }

    private static void setNqueen(int nowr, int nowc) {
        if(nowr==N){
            res++;
            return;
        }
        queen[nowr] = nowc;
        int nr = nowr+1;
        for (int nc = 1; nc <=N; nc++) {
            boolean check = false;
            //nr, nc가 가능한지 판단
            for (int i = 1; i <=nowr; i++) {
                int pastr = i;
                int pastc= queen[i];
                if(nc == pastc || nr-pastr == Math.abs(nc-pastc)) {
                    check = true;
                    break;
                }
            }
            if(check)continue;
            setNqueen(nr,nc);
        }



    }

}