import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,M,X;
    private static List<List<Node>> li;
    private static int[][] dp;
    private static int res = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        li = new ArrayList<>();
        dp = new int[N+1][N+1];
        //1 ~ N 까지 리스트 생성
            // dp 테이블(최단경로) 모두 INF로 초기화
        for (int i = 0; i <= N; i++) {
            li.add(new ArrayList<>());
            for (int j = 0; j <= N ; j++) {
                // 같을 때는 weight 0으로
                if(i == j) continue;
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //연결하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            li.get(from).add(new Node(to, weight));
        }
        for (int i = 1; i <=N ; i++) {
            dijkstra(i);
        }


        //res는 X to i + i to X의 최댓값
        for (int i = 1; i <= N; i++) {
            if(X==i) continue;
            res = Math.max(dp[X][i] + dp[i][X], res);
        }
        System.out.println(res);


    }
    private static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //시작점과 연결되어있는 노드 pq에 넣기
        pq.add(new Node(start,0));

        while (!pq.isEmpty()){
            Node now = pq.poll();

            if(dp[start][now.index] < now.weight) continue;

            for (int i = 0; i < li.get(now.index).size(); i++) {
                //now 노드의 인접노드
                Node next = li.get(now.index).get(i);
                //now노드의 인접노드를 통해서 가는 방법
               int nextDisttance = dp[start][now.index] + next.weight;

                if(nextDisttance < dp[start][next.index]){
                    dp[start][next.index] = nextDisttance;
                    pq.add(next);
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

        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
}