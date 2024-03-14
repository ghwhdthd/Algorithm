import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n,from,to,m;
    private static List<List<Integer>> p;
    private static boolean[] visit;
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        visit = new boolean[n+1];
        p = new ArrayList<>();
        parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            p.add(new ArrayList<>());
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
            p.get(a).add(b);
            p.get(b).add(a);
        }
        int aRoot=find(from);
        int bRoot=find(to);
        if(aRoot!=bRoot){
            System.out.println(-1);
            return;
        }
        visit[from] = true;
        dfs(from, 0);

    }
    private static void union(int a, int b){
        int aRoot= find(a);
        int bRoot= find(b);
        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
        return;
    }
    private static int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }
    private static void dfs(int node, int depth){
        if(node == to) {
            System.out.println(depth);
            return;
        }
        for (int i = 0; i <p.get(node).size() ; i++) {
            int next = p.get(node).get(i);
            if(visit[next]) continue;
            visit[next] = true;
            dfs(next, depth +1);
        }
    }

}