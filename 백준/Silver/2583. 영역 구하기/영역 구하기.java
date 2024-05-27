import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N,M,K;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dr = {0, 0, 1, -1};
    private static int[] dc = {-1, 1, 0, 0};
    private static List<Integer> res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visit = new boolean[M][N];
        res = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken()) - 1;
            int ry = Integer.parseInt(st.nextToken()) - 1;

            for (int j = ly; j <= ry; j++) {
                for (int k = lx; k <= rx ; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1) continue;
                if(visit[i][j]) continue;

                res.add(bfs(i, j, 0));
            }
        }

        Collections.sort(res);
        System.out.println(res.size());
        for (int n : res) {
            System.out.print(n + " ");
        }

    }
    private static int bfs(int r, int c, int count){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c));
        visit[r][c] = true;

        while(!que.isEmpty()){
            Node now = que.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;

                if(nr < 0 || nc < 0 || nr >= M || nc >= N) continue;
                if(visit[nr][nc]) continue;
                if(map[nr][nc] == 1) continue;
                visit[nr][nc] = true;
                que.add(new Node(nr, nc));

            }
        }

        return count;
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