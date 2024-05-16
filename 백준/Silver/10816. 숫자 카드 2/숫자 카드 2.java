import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
           int n = Integer.parseInt(st.nextToken());
           if(map.get(n) == null){
               map.put(n, 1);
           }else{
               int before = map.get(n);
               map.put(n, before + 1);
           }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int m = Integer.parseInt(st.nextToken());
            if(map.get(m) == null){
                sb.append(0);
            }else{
                sb.append(map.get(m));
            }
            sb.append(" ");
        }
        System.out.println(sb);

    }

}