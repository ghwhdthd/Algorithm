import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] input;
    private static int[][] island;
    private static boolean[][] visit;
    private static int MIN = Integer.MAX_VALUE;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        island = new int[N][N];
        visit = new boolean[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
               int landOrSea = Integer.parseInt(st.nextToken());
               input[i][j] = landOrSea;
            }
        }

        int islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                if(input[i][j] == 0) continue;
                bfs(i,j,islandNum);
                islandNum++;
            }
        }
        //섬 번호 부여 완료


        //섬 간 거리 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visit = new boolean[N][N];
                if(island[i][j] == 0) continue;
                int dis = getDistance(i, j, island[i][j],0);
                if(dis > 0){
                    MIN = Math.min(dis, MIN);
                }

            }
        }

        System.out.println(MIN-1);

    }
    private static int getDistance(int r, int c, int islandNum, int dis){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c, 0));
        visit[r][c] = true;
        while(!que.isEmpty()){
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
                if(visit[nr][nc])continue;
                if(island[nr][nc] == islandNum) continue;

                if(island[nr][nc] > 0) return now.depth+1;
                visit[nr][nc] = true;
                que.add(new Node(nr, nc, now.depth+1));


            }
        }
        return -1;
    }
    private static void bfs(int r, int c, int islandNum){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c, 0));
        visit[r][c] = true;
        while(!que.isEmpty()){
            Node now = que.poll();
            island[now.r][now.c] = islandNum;
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
                if(input[nr][nc] == 0) continue;
                if(visit[nr][nc]) continue;

                visit[nr][nc] = true;
                que.add(new Node(nr, nc, now.depth+1));
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