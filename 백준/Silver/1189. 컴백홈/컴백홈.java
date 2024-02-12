import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R,C,K;
    private static char[][] map;
    private static boolean[][] visit;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    private static int[] home = new int[2];
    private static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visit = new boolean[R][C];

        for (int i = 0; i <R ; i++) {
            String s = br.readLine();
            for (int j = 0; j <s.length(); j++) {
                map[i][j] = s.charAt(j);
            }
        }
        home[0] = 0;
        home[1] = C-1;
        visit[R-1][0] = true;
        dfs(R-1, 0,1);
        System.out.println(res);
    }
    private static void dfs(int nowr, int nowc, int dis){
        if(home[0] == nowr && home[1] == nowc){
            if(dis == K) res++;
            return;
        }


        for (int i = 0; i < 4; i++) {
            int nr = nowr + dr[i];
            int nc = nowc + dc[i];
            if(nr < 0 || nc <0 || nr >=R || nc >=C) continue;
            if(visit[nr][nc]) continue;
            if(map[nr][nc] == 'T') continue;
            visit[nr][nc] = true;
            dfs(nr,nc, dis+1);
            visit[nr][nc] = false;

        }
    }
}