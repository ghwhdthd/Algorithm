import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static char[][] map;
    private static int[][] time;
    private static int[][] dp;
    private static int R,C;
    private static int[] start;
    private static int[] end;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        time = new int[R][C];
        dp = new int[R][C];
        start = new int[2];
        end = new int[2];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                dp[i][j] = Integer.MAX_VALUE;
                //start, end 좌표 저장
                if(map[i][j] == 'T') {
                    start[0] = i;
                    start[1] = j;
                    time[i][j] = 0;
                }
                else if(map[i][j] == 'E'){
                    end[0] = i;
                    end[1] = j;
                    time[i][j] = 0;
                }else if(map[i][j] == 'R' || map[i][j] == 'H'){
                    time[i][j] = 0;
                }else{
                    time[i][j] = map[i][j] - '0';
                }
            }
        }
        //input 끝

        dijkstra(start);
        if(dp[end[0]][end[1]] == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(dp[end[0]][end[1]]);
        }


    }
    private static void dijkstra(int[] start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start[0], start[1], 0));
        dp[start[0]][start[1]] = 0;
        while (!pq.isEmpty()){
            Node now = pq.poll();
            //사방탐색
            for (int i = 0; i < 4; i++) {
                int nr =  now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr < 0 || nc <0 || nr >=R || nc >=C) continue;
                //nr, nc 방향으로 갈 수 있는지 체크
                int[] point = check(now.r, now.c, i, now.r, now.c);
                if(point[2] < 0) continue;

                //직접 간 경우 VS 거쳐서 간 경우
                //직접 간 경우 = dp[point[0]][point[1]]
                // 현재까지 온 최단거리
                int cur = dp[now.r][now.c];
                // point를 거쳐서 간 거리
                int nextDistance = cur + point[2];

                //기존의 직접 간 경우보다 next를 거쳐서 간 경우가 더 짧으면 pq에 넣기
                // dp table 갱신
                if(dp[point[0]][point[1]] > nextDistance){
                    dp[point[0]][point[1]] = nextDistance;
                    if(map[point[0]][point[1]] != 'E')
                    pq.add(new Node(point[0],point[1],nextDistance));
                }

            }
        }

    }
    private static int[] check(int r, int c, int dir, int sr, int sc){
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (map[r][c] == 'E') {
            int[] res ={r, c, time[r][c]};
            return res;
        }
        if(nr < 0 || nc < 0 || nr >= R || nc >= C){
            int[] res ={r, c, Integer.MIN_VALUE};
            return res;
        }
        if(map[nr][nc] == 'H' || map[nr][nc] == 'T'){
            int[] res ={r, c, Integer.MIN_VALUE};
            return res;
        }
        if(map[nr][nc] == 'R'){
            int[] res ={r, c, time[r][c]};
            return res;
        }


        int[] res = check(nr, nc, dir, sr, sc);

        //시작점을 탐지해서 그건 빼고 넘기기
        if(sr == r && sc == c){
            return res;
        }else{
            res[2] += time[r][c];
        }
        return res;
    }


    private static class Node implements Comparable<Node>{
        int r;
        int c;
        int w;
        public Node(int r, int c, int w){
            this.r = r;
            this.c = c;
            this.w = w;
        }

        public int compareTo(Node o){
            return this.w - o.w;
        }
    }

}