import java.io.*;
import java.util.*;


public class Main {
    private static boolean[] prime;
    private static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        prime = new boolean[N + 1];
        getPrime();

        for(int i = M; i <= N; i++) {
            // false = 소수
            if(!prime[i]) System.out.println(i);
        }
    }

    // 에라토스테네스의 체 알고리즘
    private static void getPrime() {
        prime[0] = prime[1] = true;
        for(int i = 2; i <= Math.sqrt(prime.length); i++) {
            if(prime[i]) continue;
            for(int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }

}