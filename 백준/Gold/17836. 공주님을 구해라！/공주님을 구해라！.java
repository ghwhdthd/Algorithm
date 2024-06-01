import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.DoubleSummaryStatistics;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, T;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dr = {0, 0, 1, -1};
    private static int[] dc = {1, -1, 0, 0};
    private static int wr, wc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];



        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    wr = i;
                    wc = j;
                }

            }
        }

        int dir = directToGoal(0,0, N-1, M-1, false, 0);
//        System.out.println(dir);
        int weapon = directToGoal(0,0, wr, wc, false, 0);
//        System.out.println(weapon);

        if (weapon < 0) {
            if(dir < 0){
                System.out.println("Fail");
            }
            else{
                if(dir > T){
                    System.out.println("Fail");
                }else{
                    System.out.println(dir);
                }


            }

        }else{
            int weaponToGoal = directToGoal(wr,wc, N-1, M-1, true, weapon);
//            System.out.println(weaponToGoal);
            if (dir < 0) {
                if (weaponToGoal > T) {
                    System.out.println("Fail");
                }else{
                    System.out.println(weaponToGoal);
                }


            }else{
                if (Math.min(dir, weaponToGoal) > T) {
                    System.out.println("Fail");
                }else{
                    System.out.println(Math.min(dir, weaponToGoal));
                }
            }

        }


    }

    private static int directToGoal(int startR, int startC, int goalR, int goalC, boolean weapon, int depth){
        Deque<Node> que = new ArrayDeque<>();
        visit = new boolean[N][M];
        que.add(new Node(startR, startC, depth, weapon));
        visit[startR][startC] = true;
        while (!que.isEmpty()){
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(visit[nr][nc]) continue;
                if (nr == goalR && nc == goalC) {
                    return now.depth+1;
                }
                if(!weapon){
                    if(map[nr][nc] != 0) continue;
                }
                que.add(new Node(nr, nc, now.depth+1, now.weapon));
                visit[nr][nc] = true;

            }
        }
        return -1;
    }
    private static class Node{
        int r;
        int c;
        int depth;
        boolean weapon;

        public Node(int r, int c, int depth, boolean weapon){
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.weapon = weapon;
        }
    }
}