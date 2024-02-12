import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int [][] map;
    private static List<Node> li = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    li.add(new Node(i,j));
                }
            }
        }

        setSdoku(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void setSdoku(int depth) {
        if(depth == li.size()){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
            return;
        }



        int nowr = li.get(depth).r;
        int nowc = li.get(depth).c;

        for (int j = 1; j <= 9; j++) {
            //값을 넣을 수 있는지 확인
            if(!isAvailable(nowr, nowc, j)) continue;

            map[nowr][nowc] = j;
            //값 넣을 수 있으면 넣고 다음거 하기
            setSdoku(depth +1);
            map[nowr][nowc] = 0;

        }



    }

    private static boolean isAvailable(int r, int c, int value) {
        //현재 행 확인
        for (int i = 0; i < 9; i++) {
            if(i == c) continue;
            if(value == map[r][i]) return false;
        }
        //현재 열 확인
        for (int i = 0; i < 9; i++) {
            if(i == r) continue;
            if(value == map[i][c]) return false;
        }

        //현재 지역 확인
        int lr = r/3; // 0, 1, 2
        int lc = c/3; // 0, 1, 2

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(r == lr*3 + i && c ==lc*3 +j ) continue;
                if(value == map[lr*3 + i][lc*3 +j]) return false;
            }

        }

        return true;
    }


    private static class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}