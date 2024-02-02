import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int [][] map;
    private static boolean [][] visit;
    private static int[] dr = {0, 0, 1, -1};
    private static int[] dc = {1, -1 ,0, 0};
    private static int maxHeight = 0;
    private static int now;
    private static int res = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > maxHeight) maxHeight = map[i][j];
            }
        }

        for (int i = 0; i <=maxHeight; i++) {
            visit = new boolean[N][N];
            now = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(visit[j][k]) continue;
                    if(map[j][k] <= i) continue;
                    bfs(i, j,k);
                    now++;
                }
                if(now > res) res = now;
            }
        }
        System.out.println(res);

    }
    private static void bfs(int water, int r, int c){
        Deque<Region> que = new ArrayDeque<>();
        que.add(new Region(r,c));
        visit[r][c] = true;
        while (!que.isEmpty()){
            Region cur = que.poll();
            //사방
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + cur.r;
                int nc = dc[i] + cur.c;
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if(visit[nr][nc]) continue;
                if(map[nr][nc] <= water) continue;
                que.add(new Region(nr,nc));
                visit[nr][nc] = true;
            }
        }


    }
    private static class Region{
        int r;
        int c;
        public Region(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
