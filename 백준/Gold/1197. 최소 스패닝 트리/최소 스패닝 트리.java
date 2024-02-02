import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int V;
    private static int E;
    private static int[] parents;
    private static int edgeCount;
    private static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V+1];

        //init
        for (int i = 0; i <=V ; i++) {
            parents[i] = i;
        }

        //간선만큼 노드 리스트 만들기
        //pq 사용하는 방법 (기본이 오름차순)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(from, to, cost));

        }

        //노드 리스트 순회하면서 가중치 작은거부터 union하고 간선의 개수가 V-1이 될때 break하기

        while (!pq.isEmpty()){
            Node node = pq.poll();
            union(node.from, node.to, node.cost);
            if(edgeCount == V-1) break;

        }
        System.out.println(res);
    }
    private static int find(int x){
        if(x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
    private static void union(int a, int b, int cost){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        parents[bRoot] = aRoot;
        edgeCount++;
        res += cost;
        return;

    }
    private static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;
        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
