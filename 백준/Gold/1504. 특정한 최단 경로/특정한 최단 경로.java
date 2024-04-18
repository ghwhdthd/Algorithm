import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,E;
    private static List<List<Node>> li;
    private static int v1,v2;
    private static int[]dp;
    private static int INF = 200_000*1000 + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        li = new ArrayList<>();
        for (int i = 0; i <=N; i++) {
            li.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            li.get(a).add(new Node(b,c));
            li.get(b).add(new Node(a,c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());


        int res = 0;
        //1 -> v1 -> v2 -> N
        int res1 = dijkstra(1,v1,0);
        res1 += dijkstra(v1,v2,0);
        res1 += dijkstra(v2,N,0);
        //1 -> v2 -> v1 -> N
        int res2 = dijkstra(1,v2,0);
        res2 += dijkstra(v2,v1,0);
        res2 += dijkstra(v1,N,0);
        if(res2 > res1){
            res = res1;
        }else{
            res = res2;
        }
        if(res >= INF) {
            System.out.println(-1);
        }else{
            System.out.println(res);
        }



    }
    private static int dijkstra(int start, int target, int weight){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp = new int[N+1];
        for (int i = 0; i <= N; i++) {
            dp[i] = INF;
        }
        dp[start] = weight;
        pq.add(new Node(start,weight));
        while (!pq.isEmpty()){
            Node now = pq.poll();
            for (int i = 0; i < li.get(now.index).size(); i++) {
                Node next = li.get(now.index).get(i);
                int nowMinDis = dp[now.index];
                int nextDis = nowMinDis+ next.weight;

                if(dp[next.index] > nextDis){
                    dp[next.index] = nextDis;
                    pq.add(new Node(next.index,nextDis));
                }
            }
        }

        return dp[target];
    }
    private static class Node implements Comparable<Node>{
        int index;
        int weight;
        public Node(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return weight-o.weight;
        }
    }
}