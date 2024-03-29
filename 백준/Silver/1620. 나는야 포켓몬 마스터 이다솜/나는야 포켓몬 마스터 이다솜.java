import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> nameToNum = new HashMap<>();
        Map<Integer, String> numToName = new HashMap<>();
        for (int i = 1; i <=N; i++) {
            String s = br.readLine();
            nameToNum.put(s,i);
            numToName.put(i,s);
        }
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            try{
                int n = Integer.parseInt(s);
                sb.append(numToName.get(n)).append('\n');

            }catch (Exception e){
                sb.append(nameToNum.get(s)).append('\n');
            }
        }
        System.out.println(sb);
    }
}