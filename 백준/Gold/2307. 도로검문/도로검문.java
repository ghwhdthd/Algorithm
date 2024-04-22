import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static List<List<Node>> li;
    private static int[] dp;
    private static int INF = 50_000_001;
    private static int MAX = Integer.MIN_VALUE;
    private static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        li = new ArrayList<>();
        dp = new int[N+1];
        for (int i = 0; i <= N; i++) {
            li.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // 양방향
            li.get(a).add(new Node(b, weight));
            li.get(b).add(new Node(a, weight));
        }

        dijkstra(1, -1);
        res = dp[N];

        for (int i = 2; i < N; i++) {
            dijkstra(1, i);
            MAX = Math.max(dp[N], MAX);
        }

        if(MAX >= INF){
            System.out.println(-1);
        }else{
            res = MAX - res;
            System.out.println(res);
        }


    }
    private static void dijkstra(int start, int checkPoint){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        for(int i=0; i<=N; i++){
            dp[i] = INF;
        }
        dp[start] = 0;
        while (!pq.isEmpty()){
            Node now = pq.poll();
            for(int i = 0; i < li.get(now.index).size(); i++){
                Node next = li.get(now.index).get(i);
                if(next.index == checkPoint) continue;
                //now 까지의 최소비용
                int nowMinDis = dp[now.index];
                //next를 거쳐서 갔을 때 비용
                int nextDis = nowMinDis + next.weight;

                if(dp[next.index] > nextDis){
                    dp[next.index] = nextDis;
                    pq.add(next);
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

        @Override
        public int compareTo(Node o){
            return weight - o.weight;
        }
    }

}