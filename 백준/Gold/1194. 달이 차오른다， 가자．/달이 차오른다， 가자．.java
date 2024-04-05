import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static char[][] map;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {-1,1,0,0};
    private static int[] start;
    private static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer( br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start =new int[2];
        map=new char[N][M];
        visit = new boolean[N][M][64];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '0') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        bfs(start[0], start[1]);
    }
    private static void bfs(int r, int c){
        Deque<Node> que =new ArrayDeque<>();
        visit[r][c][0] = true;
        que.add(new Node(r,c,0,0));

        while (!que.isEmpty()){
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(visit[nr][nc][now.key]) continue;
                if(map[nr][nc] == '#') continue;
                //목적지 도착
                if(map[nr][nc] == '1'){
                    System.out.println(now.depth+1);
                    return;
                }
                
                if(map[nr][nc] == '.' || map[nr][nc] == '0'){
                    visit[nr][nc][now.key] = true;
                    que.add(new Node(nr,nc,now.depth+1,now.key));
                }
                //door를 만난 경우
                else if(map[nr][nc] >= 'A' && map[nr][nc] <= 'F'){
                    int shiftCount = map[nr][nc] -'A';
                    int needKey =1 << shiftCount;
                    int commonBit = needKey & now.key;
                    if(commonBit !=0 ){
                        visit[nr][nc][now.key]=true;
                        que.add(new Node(nr,nc,now.depth+1,now.key));
                    }
                }
                //key를 만났을 때
                else{
                    int shiftCount = map[nr][nc] - 'a';
                    int findKey = 1 << shiftCount;
                    int nextKey = now.key | findKey;
                    visit[nr][nc][nextKey] = true;
                    que.add(new Node(nr,nc,now.depth+1,nextKey));
                }
            }
        }

        System.out.println(-1);
    }

    private static class Node{
        int r;
        int c;
        int depth;
        int key;
        public Node(int r, int c, int depth, int key){
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.key = key;
        }

    }

}