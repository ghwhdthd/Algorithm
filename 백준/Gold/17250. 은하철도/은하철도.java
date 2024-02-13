



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;

    static int[] galaxyArr;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        galaxyArr = new int[N+1];
        for(int i = 1; i<=N; i++){
            galaxyArr[i] = Integer.parseInt(br.readLine());
        }

        init();
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long res=union(a,b);
            sb.append(res);
            sb.append("\n");

        }

        bw.write(sb.toString());
        bw.flush();
        
    }
    private static void init(){
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }
    }
    private static int union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot){
            //bRoot의 부모를 aRoot 로 설정
            parent[bRoot] = aRoot;
            //aRoot 가 부모임
            galaxyArr[aRoot] += galaxyArr[bRoot];
        }
        return galaxyArr[aRoot];
    }
    private static int find(int x){
        //부모를 찾으려고 하는데 부모가 자신이면 바로 return
        if(parent[x] == x) return x;

        //부모의 부모를 찾아서 넣기 (결과적으로 최고 부모를 찾아서 갱신해줌)
        return parent[x] = find(parent[x]);
    }

}
