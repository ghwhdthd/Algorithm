import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    // 모든 구역을 나눠서 해당 구역에 몇개가 있는지 미리 count 한 뒤 map에 저장
    private static int N,M;
    private static int[][] input;
    private static int[][] visit;
    private static int[] dr ={0,0,1,-1};
    private static int[] dc ={1,-1,0,0};
    private static Map<Integer,Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        input = new int[N][M];
        map = new HashMap<>();
        map.put(0,0);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {

                input[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }
        int number = 1;
        visit = new int[N][M];

        //bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if(input[i][j] != 0) continue;
                if(visit[i][j] != 0) continue;
                int n = bfs(i,j, number);
                map.put(number,n);
                number++;

            }

        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(visit[i][j]);
//            }
//            System.out.println();
//        }
//
//        for(Integer key :map.keySet()){
//            int value = map.get(key);
//            System.out.println(key + " " + value);
//        }


        //count
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(input[i][j] != 0){
                    input[i][j]=count(i,j);
                }
                sb.append(input[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int count(int r, int c){
        int cnt=1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >=N || nc >=M) continue;
            int key = visit[nr][nc];
            set.add(key);
        }
        
        for(Integer key : set){
            cnt+= map.get(key);
        }
        return cnt%10;
    }
    private static int bfs(int r, int c, int number){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r,c,0));
        visit[r][c] = number;
        int cnt = 1;

        while (!que.isEmpty()){
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;

                if(nr < 0 || nc < 0 || nr >=N || nc >= M) continue;
                if(visit[nr][nc] != 0 ) continue;
                if(input[nr][nc] != 0 ) continue;
                visit[nr][nc] = number;
                cnt++;
                que.add(new Node(nr,nc,now.depth+1));

            }

        }


        return cnt;


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