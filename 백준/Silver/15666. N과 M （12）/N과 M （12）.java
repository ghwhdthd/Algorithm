import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[] input;
    private static int[] output;
    private static StringBuilder sb;
    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        set = new LinkedHashSet<>();

        input = new int[N];
        output = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        comb(0,0);
        sb = new StringBuilder();
        for(String s : set){
            sb.append(s).append('\n');
        }
        System.out.println(sb);
    }
    private static void comb(int depth, int start){
        if(depth == M){
            sb = new StringBuilder();
            for(int n : output){
                sb.append(n).append(" ");
            }
            set.add(sb.toString());
            return;
        }
        for (int i = start; i < N; i++) {
            output[depth] = input[i];
            comb(depth+1, i);

        }


    }
}