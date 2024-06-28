import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] dr = {-1, 0, 1, 0}; // U R D L
    private static int[] dc = {0, 1, 0, -1};
    private static boolean[][] visit;
    private static char[][] board;
    private static int N, M;
    private static int safeZone;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visit[i][j]) continue;
                safeZone++;
                bfs(i, j);
            }
        }

        System.out.println(safeZone);
    }
    private static void bfs(int r, int c){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c));
        visit[r][c] = true;
        while(!que.isEmpty()){
            Node now = que.poll();
            int nextDir = getNextDir(now.r, now.c);
            int nr = dr[nextDir] + now.r;
            int nc = dc[nextDir] + now.c;
            if(inBound(nr, nc)){
                if(!visit[nr][nc]){
                    visit[nr][nc] = true;
                    que.add(new Node(nr, nc));
                }
            }



            for (int i = 0; i < 4; i++) {
                int br = dr[i] + now.r;
                int bc = dc[i] + now.c;
                if(!inBound(br, bc)) continue;
                if(visit[br][bc]) continue;

                int beforeDir = getNextDir(br, bc);
                if(now.r == dr[beforeDir] + br && now.c == dc[beforeDir] + bc){
                    visit[br][bc] = true;
                    que.add(new Node(br, bc));
                }
            }
        }
    }

    private static boolean inBound(int nr, int nc){
        if(nr < 0 || nc < 0 || nr >= N || nc >= M) return false;
        return true;
    }
    private static int getNextDir(int r, int c){
        // U R D L
        if(board[r][c] == 'U') return 0;
        else if(board[r][c] == 'R') return 1;
        else if (board[r][c] == 'D') return 2;
        else return 3;
    }

    private static class Node{
        int r;
        int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}