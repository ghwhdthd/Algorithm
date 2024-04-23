import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    private static int V, E;
    private static int[] dp;
    private static int INF = 3_000_001;
    private static List<List<Node>> li;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        li = new ArrayList<>();

        for(int i=0; i<=V; i++){
            li.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            li.get(a).add(new Node(b,w));
        }
        dijkstra(start);

        for(int i=1; i<dp.length; i++){
            sb.append(dp[i] >= INF ? "INF" : dp[i]).append('\n');
        }
        System.out.println(sb);
    }
    private static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dp = new int[V+1];
        for(int i = 0; i <= V; i++){
            dp[i] = INF;
        }
        dp[start] = 0;

        while (!pq.isEmpty()){
            Node now = pq.poll();
            for(int i=0; i<li.get(now.index).size(); i++){
                int nowMinDis = dp[now.index];
                Node next = li.get(now.index).get(i);
                int candi = nowMinDis + next.weight;


                if(dp[next.index] > candi){
                    dp[next.index] = candi;
                    pq.add(new Node(next.index, dp[next.index]));
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