import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        int[] plan = new int[M];
        //init
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }


        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=N ; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1) {
                    union(i,j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }


        int tmpParent = find(plan[0]);
        boolean check = true;
        for (int i = 0; i < M; i++) {
            if(tmpParent != find(plan[i])){
                check = false;
                break;
            }
        }
        if(check){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }

    private static void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
    }
    private static int find(int a){
        if(a == parent[a]) return a;

        return parent[a]=find(parent[a]);
    }
}