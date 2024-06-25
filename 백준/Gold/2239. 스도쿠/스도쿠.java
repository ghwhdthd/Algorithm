import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] board;
    private static boolean isOver;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }
        isOver = false;
        dfs(0,0);

    }
    private static void dfs(int r, int c){
        if(isOver) return;
        if(c == 9) {
            c = 0;
            r++;
        }

        if(r == 9){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            isOver = true;
            return;
        }

        if(board[r][c] != 0) {
            dfs(r, c + 1);
        }
        else {
            for (int k = 1; k <= 9; k++) {
                if(!isPossible(r, c, k)) continue;
                board[r][c] = k;
                dfs(r, c + 1);
                if(isOver) return;
                board[r][c] = 0;
            }
        }

    }
    private static boolean isPossible(int r, int c, int n){
        //같은 행 확인
        for (int i = 0; i < 9; i++) {
            if(board[r][i] == n) return false;
        }
        //같은 열 확인
        for (int i = 0; i < 9; i++) {
            if(board[i][c] == n) return false;
        }
        //같은 공간 확인
        if(!isVaild(r, c, n)) return false;
        return true;
    }
    private static boolean isVaild(int r, int c, int n){
        int startR = (r / 3) * 3;
        int startC = (c / 3) * 3;
        for (int i = startR; i < startR + 3; i++) {
            for (int j = startC; j < startC + 3; j++) {
                if(r == i && j == c) continue;
                if(board[i][j] == n) return  false;
            }
        }
        return true;

    }
}