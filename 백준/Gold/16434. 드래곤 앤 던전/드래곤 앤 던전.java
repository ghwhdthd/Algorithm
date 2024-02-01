
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long left;
    private static long mid;
    private static long right;

    private static long[][] input;
    private static int N;
    private static int atk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        input = new long[N][3];
        long tmpAtk = atk;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Long.parseLong(st.nextToken());
            input[i][1] = Long.parseLong(st.nextToken());
            input[i][2] = Long.parseLong(st.nextToken());
            if(input[i][0] == 1){
                if(input[i][2] % tmpAtk == 0){
                    right += ((input[i][2] / tmpAtk)-1) * input[i][1];
                }else{
                    right += (input[i][2] / tmpAtk) * input[i][1];
                }
            }else{
                tmpAtk += input[i][1];
            }
        }
        left = 1;
        right = Long.MAX_VALUE;

        while (left <= right){
            mid = (left + right) /2;
            long nowHp = mid;
            long nowAtk = atk;
            boolean check = true;
            for (int i = 0; i < N; i++) {
                if(input[i][0] == 1){
                    nowHp -= ((input[i][2] / nowAtk)-(input[i][2] % nowAtk == 0 ? 1 : 0)) * input[i][1];
                    if(nowHp <= 0){
                        check = false;
                        break;
                    }
                }else{
                    nowAtk += input[i][1];
                    nowHp += input[i][2];
                    if(nowHp > mid) nowHp = mid;
                }
            }
            if(check) right = mid -1;
            else left = mid +1;

        }
        System.out.println(left);
    }
}