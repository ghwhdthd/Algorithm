import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] parent;
    private static int res;
    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<NodeX> xList = new ArrayList<>();
        List<NodeY> yList = new ArrayList<>();
        List<NodeZ> zList = new ArrayList<>();
        parent = new int[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            xList.add(new NodeX(x,i));
            yList.add(new NodeY(y,i));
            zList.add(new NodeZ(z,i));
            parent[i] = i;
        }
        Collections.sort(xList);
        Collections.sort(yList);
        Collections.sort(zList);
        for (int i = 0; i < N-1; i++) {
            int xw = Math.abs(xList.get(i+1).n - xList.get(i).n);
            int yw = Math.abs(yList.get(i+1).n - yList.get(i).n);
            int zw = Math.abs(zList.get(i+1).n - zList.get(i).n);


            pq.offer(new Node(xList.get(i).index,xList.get(i+1).index,xw));
            pq.offer(new Node(yList.get(i).index,yList.get(i+1).index,yw));
            pq.offer(new Node(zList.get(i).index,zList.get(i+1).index,zw));
        }

        while (!pq.isEmpty()){
            Node now =pq.poll();
            union(now);
        }
        System.out.println(res);
    }
    private static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(Node now){
        int aRoot = find(now.from);
        int bRoot = find(now.to);
        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
        res+=now.weight;
    }

    private static class NodeX implements Comparable<NodeX>{
        int n;
        int index;

        public NodeX(int n, int index){
            this.n = n;
            this.index = index;
        }
        @Override
        public int compareTo(NodeX o) {
            return this.n - o.n;
        }
    }
    private static class NodeY implements Comparable<NodeY>{
        int n;
        int index;

        public NodeY(int n, int index){
            this.n = n;
            this.index = index;
        }
        @Override
        public int compareTo(NodeY o) {
            return this.n - o.n;
        }
    }
    private static class NodeZ implements Comparable<NodeZ>{
        int n;
        int index;

        public NodeZ(int n, int index){
            this.n = n;
            this.index = index;
        }
        @Override
        public int compareTo(NodeZ o) {
            return this.n - o.n;
        }
    }

    private static class Node implements Comparable<Node>{
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return this.weight - o.weight;
        }

    }
}