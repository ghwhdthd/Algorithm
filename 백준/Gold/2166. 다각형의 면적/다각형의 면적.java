import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long[][] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new long[N+1][2];
        StringTokenizer st ;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }
        input[N][0] = input[0][0];
        input[N][1] = input[0][1];


        long n1 = 0;
        long n2 = 0;
        for (int i = 0; i < N; i++) {
            n1 += input[i][0] * input[i+1][1];
            n2 += input[i+1][0] * input[i][1];
        }
        String res = String.format("%.1f", (Math.abs(n1 - n2) / 2.0));
        System.out.println(res);

    }
}