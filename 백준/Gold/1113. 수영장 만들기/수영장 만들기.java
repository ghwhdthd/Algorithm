import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[][] map;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    private static int max = Integer.MIN_VALUE;
    private static int res;
    private static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                if(max < map[i][j]) max = map[i][j];
            }
        }
        visit = new boolean[N][M];
        //작은 숫자부터 탐색
        for (int k = 1; k <= max-1; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == k && !visit[i][j]){
                        bfs(i,j);
                    }
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] +" ");
//            }
//            System.out.println();
//        }
        System.out.println(res);
    }
    private static void bfs(int r, int c){
        Deque<Node> que = new ArrayDeque<>();
        visit[r][c] = true;
        que.add(new Node(r,c));
        boolean flood = false;
        //후보군 리스트
        List<Node> candi = new ArrayList<>();
        //자기 자신 후보군으로 넣고 시작
        candi.add(new Node(r,c));
        int aroundMinHeight = 10;

        while (!que.isEmpty()){
            Node now = que.poll();
            //now가 가장자리면 넘침
            if(now.r == 0 || now.c ==0 ||now.r ==N-1 || now.c == M-1 ){
                flood = true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                //옆에가 나보다 작으면 넘침
                if(map[nr][nc] < map[r][c]) flood = true;
                if(visit[nr][nc]) continue;

                //후보군 찾기
                if(map[nr][nc] == map[r][c]){
                    candi.add(new Node(nr,nc));
                    visit[nr][nc] = true;
                    que.add(new Node(nr,nc));
                }else if (map[nr][nc] > map[r][c]){
                    //map[r][c] 보다 큰 애들 중에 가장 작은 값을 찾기
                    //map[r][c]보다 더 크면 벽을 만난거라서 해당 nr,nc 는 더이상 탐색할 필요 없음(que에 안넣음)
                    aroundMinHeight = Math.min(aroundMinHeight,map[nr][nc]);
                }
            }
        }
        //넘치지 않았다면 찾은 후보군들 전부 물 채우고 res에 채운 양 저장
        if(!flood ){
            for (int i = 0; i < candi.size(); i++) {
                Node node = candi.get(i);
                res += (aroundMinHeight-map[node.r][node.c]);
                visit[node.r][node.c] = false;
                map[node.r][node.c] = aroundMinHeight;
            }
        }
        
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