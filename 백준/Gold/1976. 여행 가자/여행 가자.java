import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[][] inputArr;
    static int[] plan;
    static int[] city;
    static int[] parent;
    static boolean check =true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //~200
        M = Integer.parseInt(br.readLine()); //~1000

        inputArr = new int[N+1][N+1]; // 1부터
        city = new int [N+1];
        parent = new int [N+1];
        plan = new int[M];

        init();

        StringTokenizer st = null;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                inputArr[i][j] = Integer.parseInt(st.nextToken());
                if(inputArr[i][j] == 1){
                    union(i,j);
                }

            }
        }
        int superParent = -1;
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<M; i++){
            plan[i] = Integer.parseInt(st.nextToken());
            if(i == 0){
                superParent = find(plan[i]);
            }
            else{
                if(find(plan[i])!= superParent){
                    check = false;
                    break;
                }
            }
        }

        if(check){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
    private static void init(){
        for(int i =1; i<=N; i++){
            city[i] = i;
            parent[i] = i;
        }
    }
    private static int find(int x){
        if(parent[x] == x) return x;
        //부모도 최신화
        return parent[x] = find(parent[x]);
    }
    private static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot){
            return;
        }
        parent[bRoot] = aRoot;
    }

}