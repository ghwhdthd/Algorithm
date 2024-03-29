import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static boolean[] isBrokens;
    private static int start = 100;
    private static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        N = Integer.parseInt(S);
        M = Integer.parseInt(br.readLine());
        isBrokens = new boolean[10];
        if(M != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int idx = Integer.parseInt(st.nextToken());
                isBrokens[idx] = true;
            }
        }


        int res = Math.abs(N - start);
        for (int i = 0; i <=999_999; i++) {
            String now = i + "";
            boolean isBroken = false;
            for (int j = 0; j < now.length(); j++) {
                int n = now.charAt(j) -'0';
                if(isBrokens[n]){
                    isBroken = true;
                    break;
                }
            }
            if(isBroken) continue;

            int tmp = Math.abs(N - i)+ now.length();
            res = Math.min(tmp,res);
        }

        System.out.println(res);
    }
}