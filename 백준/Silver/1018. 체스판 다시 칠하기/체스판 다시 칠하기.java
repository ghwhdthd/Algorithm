import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static boolean[][] visit1;
    private static boolean[][] visit2;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    private static int min = Integer.MAX_VALUE;
    private static String[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(N-i < 8 || M-j <8) continue;
                visit1 = new boolean[N][M];
                visit2 = new boolean[N][M];
                bfs(i, j);
            }
        }
        System.out.println(min);

    }
    private static void bfs(int r, int c){
        int startR = r;
        int startC = c;
        int endR = startR + 7;
        int endC = startC + 7;
        int cnt1 = 0;
        int cnt2 = 0;
        
        Deque<Node> que1 = new ArrayDeque<>();
        que1.add(new Node(r,c,1));

        visit1[r][c] = true;
        //흰색으로 시작
        while (!que1.isEmpty()){
            Node now =que1.poll();
            if(now.depth % 2 == 0) {
                if(map[now.r].charAt(now.c) != 'B') cnt1++;
            }else {
                if (map[now.r].charAt(now.c) != 'W') cnt1++;
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(startR > nr || startC > nc || endR < nr || endC < nc) continue;
                if(visit1[nr][nc]) continue;

                visit1[nr][nc] = true;

                que1.add(new Node(nr, nc, now.depth+1));
            }
        }

        //검은색으로 시작
        Deque<Node> que2 = new ArrayDeque<>();
        que2.add(new Node(r,c,1));

        visit2[r][c] = true;
        while (!que2.isEmpty()){
            Node now =que2.poll();
            if(now.depth % 2 == 0) {
                if(map[now.r].charAt(now.c) != 'W') cnt2++;
            }else {
                if (map[now.r].charAt(now.c) != 'B') cnt2++;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(startR > nr || startC > nc || endR < nr || endC < nc) continue;
                if(visit2[nr][nc]) continue;

                visit2[nr][nc] = true;
                que2.add(new Node(nr, nc, now.depth+1));
            }
        }

        int tmpMin = Math.min(cnt1, cnt2);
        min = Math.min(min,tmpMin);

    }

    private static class Node{
        int r;
        int c;
        int depth;
        public Node(int r, int c, int depth){
            this.r=r;
            this.c=c;
            this.depth = depth;
        }
    }
}