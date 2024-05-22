import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] parent;
    private static List<List<Node>> li;
    private static int res;
    private static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        li = new ArrayList<>();
        //init
        for (int i = 0; i <= N; i++) {
            li.add(new ArrayList<>());
            parent[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b, w));
        }

        while (!pq.isEmpty()){
            Node now = pq.poll();
            union(now.a, now.b, now.weight);
        }
        System.out.println(res);
    }
    private static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b, int w){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
        if(count == N-2) return;
        res += w;
        count++;
    }

    private static class Node implements Comparable<Node>{
        int a;
        int b;
        int weight;

        public Node(int a, int b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return weight - o.weight;
        }

    }
}