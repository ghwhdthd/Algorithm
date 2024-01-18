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
    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        output = new int[M];

        set = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
           input[i] = Integer.parseInt(st.nextToken());
        }
        // 입력 끝

        // 정렬
        Arrays.sort(input);

        perm(0);

        //출력
        for(String s : set){
            sb.append(s).append('\n');
        }
        System.out.println(sb);

    }
    private static void perm(int depth){
        if(depth == M){
            String s = new String();
            for (int i = 0; i < output.length; i++) {
                s += output[i] + " ";
            }
            set.add(s);
            return;
        }
        for (int i = 0; i < N; i++) {
            output[depth] = input[i];
            perm(depth+1);
        }
    }
}