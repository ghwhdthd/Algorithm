import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());
            if(now == 0){
                if(!pq.isEmpty()){
                    sb.append(pq.poll()).append('\n');
                }
                else{
                    sb.append(0).append('\n');
                }
            }else{
                pq.add(now);
            }

        }
        System.out.println(sb);

    }
}