import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int N;

    private static StringBuilder sb =new StringBuilder();
    private static long dp[] = new long[101];
    private static List<BigInteger> li = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[1] = 1;
        li.add(BigInteger.valueOf(0));
        li.add(BigInteger.valueOf(1));
        N = Integer.parseInt(br.readLine());
        for (int i = 2; i <=100; i++) {
            dp[i] = dp[i-1]*2 +1;
        }
        if(N<=20){
            hanoi(N,1,2,3);
            sb.insert(0,dp[N] + "\n");
        }else{
            BigInteger b = new BigInteger("2");
            b = b.pow(N);
            b=b.subtract(new BigInteger("1"));
            sb.append(b);
        }

        System.out.println(sb);

    }

    private static void hanoi(int n, int from, int temp, int to){
        if(n <= 0) {
            return;
        }
        //from에서 temp로 옯기기

        hanoi(n-1,from,to,temp);
        if(N<=20)sb.append(from +" " + to).append('\n');
        //temp에서 to로 옯기기
        hanoi(n-1,temp,from,to);

    }
}