import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());
            if(now == 0){
                if (pq.isEmpty()) {
                    sb.append(0).append('\n');
                }else{
                    sb.append(pq.poll()).append('\n');
                }
            }
            else pq.add(now);
        }
        System.out.println(sb);

    }
}