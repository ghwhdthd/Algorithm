import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[][] inputMap;
    private static int[][] island;
    private static boolean[][] visit;
    private static int[] parents;
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    private static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputMap = new int[N][M];
        visit = new boolean[N][M];
        island = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                inputMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(inputMap[i][j] == 0) continue;
                if(visit[i][j]) continue;
                bfs(i, j, islandNum);
                islandNum++;
            }
        }//섬 번호 부여 끝

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(island[i][j] + " ");
//            }
//            System.out.println();
//        }

        parents = new int[islandNum];
        //init
        for (int i = 1; i < islandNum; i++) {
            parents[i] = i;
        }

        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        //섬 끼리 연결하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(island[i][j] == 0) continue;
                //a -> b : distance
                for (int k = 0; k < 4; k++) {
                    //dfs로 두 섬간 거리 구하기(다리 구하기)
                    Bridge now = getBridge(i, j, island[i][j], k);
                    if(now != null){

                        if(now.distance < 2) continue;
//                        System.out.println(now.a + " -> " + now.b + " : " + now.distance);
                        pq.add(now);
                    }
                }

            }
        }

        //이제 각 섬들 union find 로 연결하기
        while (!pq.isEmpty()){
            Bridge now = pq.poll();
            union(now.a, now.b, now.distance);
        }
        //모두 같은 parent 이면 모든 섬이 연결된 거임
        // 모두 같은 parent 인지 확인하는 코드
        int parent = find(1);
        for (int i = 1; i < islandNum; i++) {
            // 다른 parent 나오면 -1 출력 후 return
            if (parent != find(i)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(res);

    }

    private static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    private static void union(int a, int b, int distance){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        parents[bRoot] = aRoot;

        //res 에 distance 추가해주기
        res += distance;

    }

    private static Bridge getBridge(int r, int c, int a, int dir){
        int nr = dr[dir] + r;
        int nc = dc[dir] + c;
        if(nr < 0 || nc < 0 || nr > N-1 || nc > M -1 || island[nr][nc] == a){
            return null;
        }
        if(island[nr][nc] != 0 && island[nr][nc] != a){
            return new Bridge(a, island[nr][nc], 0);
        }

        Bridge next = getBridge(nr, nc, a, dir);
        if(next == null) return null;
        else{
            next.distance++;
            return next;
        }


    }
    private static void bfs(int r, int c, int islandNum){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c));
        visit[r][c] = true;
        while (!que.isEmpty()){
            Node now = que.poll();
            island[now.r][now.c] = islandNum;
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
                if(visit[nr][nc]) continue;
                if(inputMap[nr][nc] == 0) continue;

                visit[nr][nc] = true;
                que.add(new Node(nr, nc));

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

    private static class Bridge implements Comparable<Bridge>{
        int a;
        int b;
        int distance;
        public Bridge(int a, int b, int distance){
            this.a = a;
            this.b = b;
            this.distance = distance;
        }
        public int compareTo(Bridge o){
            return distance - o.distance;
        }
    }
}