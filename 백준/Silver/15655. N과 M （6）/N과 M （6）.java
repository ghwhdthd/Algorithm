import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[] input;
    private static int[] output;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb= new StringBuilder();
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input =new int[N];
        output = new int[M];
        st= new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        //입력 끝

        // 정렬
        Arrays.sort(input);

        //조합
        comb(0,0);
        System.out.println(sb);
    }
    private static void comb(int depth, int start){
        if(depth == M){
            for (int i = 0; i < output.length; i++) {
                sb.append(output[i]).append(" ");
            }
            sb.append('\n');

            return;
        }
        for (int i = start; i < input.length; i++) {
            output[depth] = input[i];
            comb(depth+1, i+1);
        }
    }
}