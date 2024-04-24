import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int start, end;
    private static List<List<Node>> li;
    private static int[] dp;
    private static int INF = 100_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        li = new ArrayList<>();
        dp = new int[N+1];
        for (int i = 0; i <=N; i++) {
            li.add(new ArrayList<>());
            dp[i] = INF;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            li.get(a).add(new Node(b, w));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dijkstra();
        System.out.println(dp[end]);
    }
    private static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()){
            Node now = pq.poll();
            //이미 구한 해당 node로 가는 비용보다 더 큰 비용이 나오면 continue 해주기
            // 이거 안하면 시간초과남.
            if(now.weight > dp[now.index] ) continue;
            for (int i = 0; i < li.get(now.index).size(); i++) {
                Node next = li.get(now.index).get(i);
                int nowMinDis = dp[now.index];
                int candi = nowMinDis + next.weight;

                if(dp[next.index] > candi){
                    dp[next.index] = candi;
                    pq.add(new Node(next.index, candi));
                }

            }
        }
    }
    private static class Node implements Comparable<Node>{
        int index;
        int weight;
        public Node(int index, int weight){
            this.index = index;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return weight - o.weight;
        }
    }
}