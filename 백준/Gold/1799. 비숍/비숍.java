import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main {
    private static int[][] board;
    private static int[][] tmp;
    private static int[][] visit;
    private static int N;
    private static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visit = new int[N][N];

        tmp = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        white(0, 0, 0);
        int res1 = res;
        res = 0;
        black(0,1, 0);
        int res2 = res;
        System.out.println(res1 + res2);

    }
    private static void white(int r, int c, int count){
        if(r >= N ){
            res = Math.max(res, count);
            return;
        }
        //nr nc 를 한번씩 건너 뛰면서 해야함.
        int nr = r;
        int nc = c + 2;
        if(nc >= N){
            nr++;
            if(nr % 2 == 1) nc = 1;
            else nc = 0;
        }
        if(visit[r][c] == 0 && board[r][c] == 1){
            //현재 자리에 놓는 경우
            change(1, r, c );
            tmp[r][c]++;
            white(nr, nc, count + 1);
            tmp[r][c]--;
            change(0, r, c );
        }
        //현재 자리에 놓지 못하는 경우 + 놓지 않는 경우
        white(nr, nc, count);



    }
    private static void black(int r, int c, int count){

        if(r >= N ){
            res = Math.max(res, count);
            return;
        }
        //nr nc 를 한번씩 건너 뛰면서 해야함.
        int nr = r;
        int nc = c + 2;
        if(nc >= N){
            nr++;
            if(nr % 2 == 1) nc = 0;
            else nc = 1;
        }
        if(visit[r][c] == 0 && board[r][c] == 1){
            //현재 자리에 놓는 경우
            change(1, r, c );
            tmp[r][c]++;
            black(nr, nc, count + 1);
            tmp[r][c]--;
            change(0, r, c );
        }
        //현재 자리에 놓지 못하는 경우 + 놓지 않는 경우
        black(nr, nc , count);



    }


    private static void change(int check, int r, int c){
        //check == 1 은 놓은 경우 0은 놓지 않은 경우

        //왼위
        for (int i = 0; i < N; i++) {
            int nr = r - i;
            int nc = c - i;
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;

            if(check == 1){
                visit[nr][nc] ++;
            }else{
                visit[nr][nc] --;
            }
        }
        //오위
        for (int i = 0; i < N; i++) {
            int nr = r - i;
            int nc = c + i;
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;

            if(check == 1){
                visit[nr][nc] ++;
            }else{
                visit[nr][nc] --;
            }
        }
        //왼아래
        for (int i = 0; i < N; i++) {
            int nr = r + i;
            int nc = c - i;
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;

            if(check == 1){
                visit[nr][nc] ++;
            }else{
                visit[nr][nc] --;
            }
        }
        //오아래
        for (int i = 0; i < N; i++) {
            int nr = r + i;
            int nc = c + i;
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;

            if(check == 1){
                visit[nr][nc] ++;
            }else{
                visit[nr][nc] --;
            }
        }
    }
}