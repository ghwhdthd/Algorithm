import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] input;
    private static int[] output;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        output = new int[M];
        for (int i = 0; i < N; i++) {
            input[i] = i+1;
        }
        comb(0, 0);
    }
    private static void comb(int start, int depth){
        if(depth >= M){
            for (int i = 0; i < M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = start; i < N; i++) {
            output[depth] = input[i];
            comb(i, depth + 1);
        }
    }
}