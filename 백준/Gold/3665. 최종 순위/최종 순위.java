import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] indegree;
    private static int[] lastYearRanking;
    private static boolean[][] isNext;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            pro(br);
        }
    }
    private static void pro(BufferedReader br) throws IOException{
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        lastYearRanking = new int[N+1];
        indegree = new int[N+1];
        isNext = new boolean[N+1][N+1];
        //진입차수 계산, 지난해 랭킹 저장
        for (int i = 1; i <= N; i++) {
            int teamNum = Integer.parseInt(st.nextToken());
            lastYearRanking[i] = teamNum;
            indegree[teamNum] = i-1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                isNext[lastYearRanking[i]][lastYearRanking[j]] = true;
            }
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(isNext[a][b]){
                isNext[a][b] = false;
                isNext[b][a] = true;
                indegree[a]++;
                indegree[b]--;
            }else{
                isNext[b][a] = false;
                isNext[a][b] = true;
                indegree[a]--;
                indegree[b]++;
            }
        }
        topologySort();

    }
    private static void topologySort(){
        Deque<Integer> que = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0){
                que.add(i);
            }
        }
        for (int i = 1; i <= N; i++) {
            if (que.size() == 0) {
                System.out.println("IMPOSSIBLE");
                return;
            }
            if(que.size() > 1 ){
                System.out.println("?");
                return;
            }
            int now = que.poll();
            sb.append(now).append(" ");
            for (int j = 1; j <= N; j++) {
                if(isNext[now][j]){
                    if(--indegree[j] == 0){
                        que.add(j);
                    }
                }
            }
        }
        System.out.println(sb);
    }
}