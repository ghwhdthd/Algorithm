import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int[] input;
    private static int[] output;
    private static int res;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            pro();
        }
        System.out.println(sb);
    }
    private static void pro()throws IOException{
        N = Integer.parseInt(br.readLine());
        input = new int[3];
        output = new int[N];
        res = 0;
        for (int i = 0; i < 3; i++) {
            input[i] = i+1;
        }
        for (int i = 1; i <=N; i++) {
            perm(0,i);
        }

        sb.append(res).append('\n');
    }
    private static void perm(int depth, int end){
        if(depth == end){
            int sum = 0;
            for (int i = 0; i < end; i++) {
                sum += output[i];
                if(sum > N) return;
            }
            if(sum == N){
                res++;
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            output[depth] = input[i];
            perm(depth+1,end);
        }
    }

}