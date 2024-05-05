import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static List<List<Node>> li;
    private static int N, M;
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        li = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            li.add(new ArrayList<>());
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            li.get(a).add(new Node(b, w));
            li.get(b).add(new Node(a, w));
        }
        dijkstra();

        System.out.println(dp[N]);
    }
    private static void dijkstra(){
        dp[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()){
            Node now = pq.poll();
            for(Node next : li.get(now.index)){
                int nowMinDis = dp[now.index];
                int nextDis = nowMinDis + next.w;

                if(dp[next.index] > nextDis){
                    dp[next.index] = nextDis;
                    pq.add(new Node(next.index, nextDis));
                }
            }
        }
    }
    private static class Node implements Comparable<Node>{
        int index;
        int w;
        public Node(int index, int w){
            this.index = index;
            this.w = w;
        }
        public int compareTo(Node o){
            return w - o.w;
        }
    }
}