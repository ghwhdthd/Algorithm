import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*

6 6
D...*.
.X.X..
...X..
......
......
S.....

**/

public class Main {
    private static int R,C;
    private static char[][] map;
    private static boolean[][] visit;
    private static boolean[][] waterVisit;
    private static int[][] waterMap;
    private static int[] dr = {0,0,-1,1};
    private static int[] dc = {-1,1,0,0};
    private static int res = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        map = new char[R][C];
        waterMap = new int[R][C];

        visit = new boolean[R][C];
        waterVisit = new boolean[R][C];
        Deque<Node> waterQue = new ArrayDeque<>();
        int sr =0;
        int sc = 0;
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '*'){
                    waterVisit[i][j] = true;
                    waterQue.add(new Node(i,j,0));
                }
                if(map[i][j] == 'S'){
                    sr = i;
                    sc = j;
                }
            }
        }

        waterBfs(waterQue);
        bfs(sr, sc);

        if(res == -1) {
            System.out.println("KAKTUS");
        }else{
            System.out.println(res);
        }


    }
    private static void waterBfs(Deque<Node> que){

        while (!que.isEmpty()){
            Node now =que.poll();
            waterMap[now.r][now.c] = now.depth;

            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;

                if(nr < 0 || nc <0 || nr >= R || nc >= C) continue;
                if(waterVisit[nr][nc]) continue;
                if(map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;

                waterVisit[nr][nc] = true;
                que.add(new Node(nr,nc, now.depth+1));
            }
        }
    }
    private static void bfs(int r, int c){
        Deque<Node> que = new ArrayDeque<>();
        visit[r][c] = true;
        que.add(new Node(r,c,0));

        while (!que.isEmpty()){
            Node now = que.poll();
            if(map[now.r][now.c] == 'D') {
                res = now.depth;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;

                if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if(visit[nr][nc]) continue;
                if(map[nr][nc] == 'X') continue;

                if(waterMap[nr][nc] <= now.depth + 1 && waterMap[nr][nc] != 0) continue;

                visit[nr][nc] = true;

                que.add(new Node(nr, nc, now.depth +1));

            }
        }
    }

    private static class Node{
        int r;
        int c;
        int depth;
        public Node(int r, int c, int depth){
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
}