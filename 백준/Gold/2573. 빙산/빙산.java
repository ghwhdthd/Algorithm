import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] visit;
    private static int[][] board;
    private static int N, M;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int depth = 1;
        boolean zero = false;
        while (true){
            melt();
            if(isAllZero()) {
                zero = true;
                break;
            }
            int mountCount = countBfs();
            if(mountCount >= 2) break;
            depth++;

        }
        if(zero){
            System.out.println(0);
        }else{
            System.out.println(depth);
        }



    }
    private static boolean isAllZero(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] !=0) return false;
            }
        }
        return true;
    }
    private static void melt(){
        List<Node> li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] != 0){
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(!boundCheck(nr, nc)) continue;
                        if(board[nr][nc] == 0) cnt++;
                    }


                    if(cnt != 0){
                        li.add(new Node(i, j, cnt));
                    }
                }
            }
        }
        for(Node now : li){
            int before = board[now.r][now.c];
            int after = before - now.n;
            if(after < 0){
                board[now.r][now.c] = 0;
            }else{
                board[now.r][now.c] = after;
            }
        }
    }
    private static boolean boundCheck(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= M) return false;
        return true;
    }

    private static int countBfs(){
        int mountCnt = 0;
        Deque<Node> que = new ArrayDeque<>();
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if(board[i][j] == 0) continue;
                if(visit[i][j]) continue;
                mountCnt ++;
                que.add(new Node(i, j, 0));
                visit[i][j] = true;
                while(!que.isEmpty()){
                    Node now = que.poll();
                    for (int k = 0; k < 4; k++) {
                        int nr = now.r + dr[k];
                        int nc = now.c + dc[k];
                        if(!boundCheck(nr, nc)) continue;
                        if(visit[nr][nc]) continue;
                        if(board[nr][nc] == 0) continue;

                        visit[nr][nc] = true;
                        que.add(new Node(nr, nc, 0));
                    }
                }
            }
        }
        return mountCnt;
    }

    private static class Node{
        int r;
        int c;
        int n;
        public Node(int r, int c, int n){
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }


}