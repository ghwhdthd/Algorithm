import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[][] map; // 40만
    private static int[][] res; // 40만
    private static boolean[][] visit; //20만
    private static int[] goal = new int[2];
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        res = new int[N][M];
        StringBuilder sb = new StringBuilder();
        visit = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                res[i][j] = -1;
                if(map[i][j] == 2) {
                    goal[0] = i;
                    goal[1] = j;
                    res[i][j] = 0;
                }
                if(map[i][j] == 0){
                    res[i][j] = 0;
                }
            }
        }

        bfs(goal[0],goal[1]);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    private static void bfs(int r, int c){
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{r,c,0});
        visit[r][c] = true;
        while (!que.isEmpty()){
            int[] now = que.poll();
            res[now[0]][now[1]] = now[2];
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(visit[nr][nc]) continue;
                if(map[nr][nc] == 0) continue;
                visit[nr][nc] = true;
                que.add(new int[]{nr,nc,now[2]+1});
            }
        }

    }
}