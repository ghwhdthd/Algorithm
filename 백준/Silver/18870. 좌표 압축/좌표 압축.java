import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] input;
    private static int[] sortInput;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        sortInput = new int[N];
        Map<Integer,Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            sortInput[i] = input[i];
        }

        Arrays.sort(sortInput);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if(!map.containsKey(sortInput[i])){
                map.put(sortInput[i], cnt++);
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append(map.get(input[i])).append(" ");
        }
        System.out.println(sb);

    }
}