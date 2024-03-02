import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
    private static int N,M;
    private static int[] parent;
    private static int truths;
    private static int[] tArr;
    private static List<Integer>[] group;
    private static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = M;
        parent = new int[N+1];
        //init
        for (int i = 0; i <=N ; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        truths = Integer.parseInt(st.nextToken());
        tArr = new int[truths];
        for (int t = 0; t < truths; t++) {
            //진실을 아는 사람들 번호 배열
            tArr[t] = Integer.parseInt(st.nextToken());
        }
        //파티 리스트
        group = new List[M];

        //input
        for (int i = 0; i < M; i++) {
            group[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int master = Integer.parseInt(st.nextToken()); // 맨 앞에 나오는 숫자를 마스터로 놓음
            group[i].add(master);
            for (int j = 1; j <size; j++) {
                int now =Integer.parseInt(st.nextToken());
                union(master, now);
                group[i].add(now);
            }
        }

        for (int i = 0; i <M; i++) {
            int master =find(group[i].get(0));
            for (int j = 0; j < tArr.length; j++) {
                if(master == tArr[j]) {
                    res--;
                    break;
                }
            }

        }


        System.out.println(res);

    }
    private static int find(int n){
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }
    private static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;
        //bRoot가 진실을 아는 사람들 중 한명이라면 bRoot 를 parent로 해주기
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] == bRoot){
                parent[aRoot] = bRoot;
                return;
            }
        }
        parent[bRoot] = aRoot;


    }
}