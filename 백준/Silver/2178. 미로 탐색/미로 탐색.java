import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[][] input;
    private static boolean[][] visit;
    private static int[] dr ={0,0,1,-1};
    private static int[] dc ={1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                input[i][j] = s.charAt(j)-'0';
            }
        }
        bfs(0,0,1);

    }
    private static void bfs(int r, int c, int depth){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r,c,depth));
        visit[r][c] = true;
        while (!que.isEmpty()){
            Node now = que.poll();
            if (now.r == N - 1 && now.c == M - 1) {
                System.out.println(now.depth);
            }
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(visit[nr][nc]) continue;
                if (input[nr][nc] == 0) continue;

                que.add(new Node(nr,nc,now.depth+1));
                visit[nr][nc] = true;
            }
        }
    }
    private static class Node{
        int r;
        int c;
        int depth;
        public Node (int r, int c, int depth){
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
}