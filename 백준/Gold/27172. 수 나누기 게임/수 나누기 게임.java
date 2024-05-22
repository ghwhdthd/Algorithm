import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] x;
    private static int[] res;
    private static Map<Integer, Integer> map;
    private static int MAX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =  new StringBuilder();
        N = Integer.parseInt(br.readLine());
        x = new int[N];
        res = new int[N];
        map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
            map.put(x[i], i);
        }

        Arrays.sort(x);
        MAX = x[N-1];

        pro();
        for (int i = 0; i < N; i++) {
            sb.append(res[i] + " ");
        }
        System.out.println(sb);
    }
    private static void pro(){
        for (int i = 0; i < N; i++) {
            int now = x[i];
            int tmp = 2;

            while (now * tmp <= MAX){
                int n = now * tmp;
                if(map.get(n) != null){
                    res[map.get(now)]++;
                    res[map.get(n)]--;
                }
                tmp++;

            }
        }
    }
}