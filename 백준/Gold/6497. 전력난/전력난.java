import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[] parent;
    private static PriorityQueue<Home> pq;
    private static int total;
    private static int used;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        while (true){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M ==0) break;
            total = 0;
            used = 0;
            parent = new int[N];
            pq = new PriorityQueue<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                total+=z;
                pq.add(new Home(x,y,z));
            }
            //init
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            while (!pq.isEmpty()){
                Home now = pq.poll();
                union(now.x, now.y,now.z);
            }
            System.out.println(total- used);

        }



    }
    private static int find(int a){
        if(a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }
    private static void union(int a, int b, int z){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;
        used +=z;
        parent[bRoot] = aRoot;

    }

    private static class Home implements Comparable<Home>{
        int x,y,z;

        public Home(int x, int y, int z){
            this.x=x;
            this.y=y;
            this.z=z;
        }

        @Override
        public int compareTo(Home o) {
            return this.z - o.z;
        }
    }
}