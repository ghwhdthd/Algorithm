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
        st = new StringTokenizer(br.readLine());

        //set의 종류 3가지
        // - HashSet : 순서 보장 X
        // - LinkedHashSet : 내가 집어넣은 순서로 저장
        // - TreeSet : 오름차순으로 정렬해서 저장 == redis 의 SortedSet
        set = new LinkedHashSet<>();

        input = new int[N];
        output = new int[M];

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        //입력 끝

        //정렬
        Arrays.sort(input);

        //조합
        comb(0,0);

        for(String s : set){
            sb.append(s).append('\n');
        }
        System.out.println(sb);
    }
    private static void comb(int depth, int start){
        if(depth == M){
            String s = new String();
            for (int i = 0; i < output.length; i++) {
                s+=output[i] + " ";
            }
            set.add(s);
            return;
        }
        for (int i = start; i < N; i++) {
            output[depth] = input[i];
            comb(depth + 1, i+1);
        }
    }
}