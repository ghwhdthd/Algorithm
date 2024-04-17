import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,D,C;
    private static int[] dp;
    private static List<List<Node>> li;
    private static int INF = 1_000_000_001;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            pro();
        }
        System.out.println(sb);
    }
    private static void pro() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        li = new ArrayList<>();
        for (int i = 0; i <=N; i++) {
            li.add(new ArrayList<>());
        }
        for (int i = 0; i <D ; i++) {
            st = new StringTokenizer(br.readLine());
            // b -> a
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            li.get(b).add(new Node(a, s));
        }

        dp = new int[N+1];
        for (int i = 0; i <= N; i++) {
            dp[i] = INF;
        }

        dijkstra(C);
        int infection = 0; // 총 감염되는 컴퓨터 수
        int total = 0; // 마지막 컴퓨터가 감염되기까지 걸리는 시간

        for (int i = 1; i < N + 1; i++) {
            if (dp[i] != INF) { // dp[i]가 INF가 아닌 것은 감염된 컴퓨터
                infection++;
                total = Math.max(total, dp[i]);
            }
        }

        sb.append(infection).append(" ").append(total).append('\n');
    }
    private static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue();
        dp[start] = 0;
        pq.add(new Node(start,0));
        while (!pq.isEmpty()){
            Node now = pq.poll();
            for (int i = 0; i < li.get(now.index).size(); i++) {
                Node next = li.get(now.index).get(i);
                //start 부터 now까지의 최소 거리
                int nowMinDis = dp[now.index];
                //nowMinDis에다가 next.weight를 더한 거리 (아직 최소 거리 아님)
                int nextDis = nowMinDis + next.weight;
                //now에서 next를 거쳐서 가는게 start에서 직접가는 거 보다 짧으면 최소 거리니까 갱신
                if(nextDis < dp[next.index]){
                    dp[next.index] = nextDis;
                    pq.add(new Node(next.index, nextDis));
                }
            }
        }
    }
    private static class Node implements Comparable<Node>{
        int index;
        int weight;
        public Node(int i, int w){
            this.index = i;
            this.weight = w;
        }
        public int compareTo(Node o ){
            return weight - o.weight;
        }
    }

}