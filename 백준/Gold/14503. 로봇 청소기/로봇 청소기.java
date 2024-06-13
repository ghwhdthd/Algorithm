import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] roomMap;
    private static int[] dr = {-1, 0, 1, 0};// 북 동 남 서
    private static int[] dc = {0, 1, 0, -1};// 북 동 남 서

    private static int[] dArr = {0, 3, 2, 1, 0, 3, 2, 1};
    private static int sr;
    private static int sc;
    private static int sd;
    private static int count;



    //1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
    //2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
    //  2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
    //  2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
    //3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
    //  3-1. 반시계 방향으로 90도 회전한다.
    //  3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
    //  3-3. 1번으로 돌아간다.

    // 1은 벽, 0은 빈칸
    // 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽이 있다.
    // 로봇 청소기가 있는 칸은 항상 빈 칸이다.
    //  로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roomMap = new int[N][M];
        st = new StringTokenizer(br.readLine());

        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        sd = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                roomMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean();
        System.out.println(count);


    }
    private static void clean(){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(sr, sc, sd));
        count++;
        roomMap[sr][sc] = 2;
        while (!que.isEmpty()){
            Node now = que.poll();
            int nd = turn(now.r, now.c, now.d);
            if(nd != -1){
                //전진
                int nr = now.r + dr[nd];
                int nc = now.c + dc[nd];
                que.add(new Node(nr, nc, nd));
                roomMap[nr][nc] = 2;
                count++;


            }else{
                //후진
                int index = 0;
                if(now.d == 3){
                    index = 1;
                }
                if(now.d == 2){
                    index = 2;
                }
                if(now.d == 1){
                    index = 3;
                }
                int nr = now.r + dr[dArr[index + 2]];
                int nc = now.c + dc[dArr[index + 2]];
                // 범위 넘어가면 후진 못하니까 리턴
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) return;
                // 벽이면 후진 못하니까 리턴
                if(roomMap[nr][nc] == 1) return;
                que.add(new Node(nr, nc, now.d));


            }

        }
    }
    //nowD가 return되면 사방 모두 청소된 상태여서 후진해야함 (반시계)
    // nowD가 아니면 해당 방향으로 전진
    private static int turn(int nowR, int nowC, int nowD){
        int[] dCount = {0, 0, 0, 0};
        int index = 0;
        if(nowD == 3){
            index = 1;
        }
        if(nowD == 2){
            index = 2;
        }
        if(nowD == 1){
            index = 3;
        }
        for (int i = index + 1; i < dArr.length; i++) {
            // dArr = {0, 3, 2, 1, 0, 3, 2, 1};;
            dCount[dArr[i]]++;
            if(dCount[dArr[i]] == 2) return -1;

            int nr = nowR + dr[dArr[i]];
            int nc = nowC + dc[dArr[i]];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

            if(roomMap[nr][nc] == 0) return dArr[i];

        }

        return -1;


    }

    private static class Node{
        int r;
        int c;
        int d;

        public Node(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}