import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int T, N;
    private static BufferedReader br;
    private static int[] wantArr;
    private static int count;
    private static boolean[] done;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            pro();
        }

    }
    private static void pro() throws IOException{
        N = Integer.parseInt(br.readLine());
        count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        wantArr = new int[N+1];
        done = new boolean[N+1];
        visit = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            int now = Integer.parseInt(st.nextToken());
            wantArr[i] = now;
            //자기자신을 원하는 경우 count + 1
            if(now == i) {
                count++;
                done[i] = true;
            }
        }



        for (int i = 1; i <= N; i++) {
            if(done[i]) continue; // 자기 자신을 원하는 경우는 continue
            dfs(i);
        }

        System.out.println(N-count);
    }
    private static void dfs(int now){
        if(done[now]) return;
        //이미 방문했던 곳을 또 온 경우는 사이클의 구성원이다.
        if(visit[now]){
            count++;
            done[now] = true;
        }
        
        
        visit[now] = true;
        int next = wantArr[now];
        dfs(next);
        //사이클이 아닌 애들도 검사 끝났으니까
        done[now] = true;
        visit[now] = false;


    }
}