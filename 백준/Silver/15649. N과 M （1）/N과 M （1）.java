import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    private static int N,M;
    private static int[] input;
    private static int[] output;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb =new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            visit[i] = false;
            input[i] = i+1;
        }
        output = new int[M];

        perm(visit, 0);
        System.out.println(sb);

    }
    private static void perm(boolean[] visit, int depth){
        if(depth == M){
            for (int i = 0; i < M; i++) {
                sb.append(output[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < N; i++) {
            if(visit[i]) continue;
            output[depth] = input[i];
            visit[i] = true;
            perm(visit, depth+1);
            visit[i] = false;
        }
    }
}
