import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static boolean[][][] visit;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N][M][2];
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        bfs(0,0);


    }
    private static void bfs(int r, int c){
        Deque<Node> que = new ArrayDeque<>();
        visit[r][c][0] = true;
        que.add(new Node(r,c,1,true));

        while(!que.isEmpty()){
            Node now = que.poll();
            if(now.r == N-1 && now.c == M-1){
                System.out.println(now.depth);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(now.hasKey){
                    if(visit[nr][nc][0]) continue;
                }else{
                    if(visit[nr][nc][1]) continue;
                }


                if(board[nr][nc] == 1){
                    if(now.hasKey) {
                        visit[nr][nc][1] = true;
                        que.add(new Node(nr, nc, now.depth + 1, false));
                    }
                    continue;
                }
                if(now.hasKey) visit[nr][nc][0] = true;
                else visit[nr][nc][1] = true;
                que.add(new Node(nr, nc, now.depth+1, now.hasKey));


            }

        }

        System.out.println(-1);


    }

    private static class Node{
        int r;
        int c;
        int depth;
        boolean hasKey;

        public Node(int r, int c, int depth, boolean hasKey){
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.hasKey = hasKey;
        }
    }
}