import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N; // 방문할 나라의 수
    private static int P; // 길의 수
    private static int[] C; // i번째 나라를 방문할 때 드는 비용
    private static int[] parent;
    private static int res;
    private static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = new int[N+1];
        parent = new int[N+1];
        res = 0;

        PriorityQueue<Country> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            C[i] = Integer.parseInt(br.readLine());
            min = Math.min(C[i], min);
            parent[i] = i;
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int country1 = Integer.parseInt(st.nextToken());
            int country2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Country(country1, country2, cost+ cost + C[country2] + C[country1]));
        }
        while(!pq.isEmpty()){
            Country now = pq.poll();
            union(now.x, now.y, now.cost);
        }
        System.out.println(res + min);
    }
    private static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    private static void union(int a, int b, int cost){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
        res+=cost;
    }
    private static class Country implements Comparable<Country>{
        int x;
        int y;
        int cost;
        public Country(int x,int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int compareTo(Country o){
            return this.cost - o.cost;
        }
    }
}