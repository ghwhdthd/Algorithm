import java.io.*;
import java.util.*;


public class Main {
    private static int N, K;
    private static Deque<Integer> que = new ArrayDeque<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            que.add(i);
        }
        sb.append("<");
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                int tmp = que.poll();
                if(j == K){
                    sb.append(tmp);
                }else{
                    que.add(tmp);
                }
            }
            if(i == N-1){
                sb.append(">");
            }else{
                sb.append(", ");
            }
        }

        System.out.println(sb);
    }


}