import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N,K;
    private static int[] map;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[200_000];
        visit = new boolean[200_000];

        bfs();
        System.out.println(map[K]);
    }
    private static void bfs(){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(N,0));
        visit[N] = true;
        while (!que.isEmpty()){
           Node now = que.poll();
           int nr = now.idx - 1;
           int nr2 = now.idx + 1;
           int nr3 = now.idx * 2;

           if(nr >= 0 && nr < map.length){
               if(!visit[nr]){
                   map[nr] = now.depth+1;
                   visit[nr] = true;
                   que.add(new Node(nr,now.depth+1));
               }

           }

            if(nr2 >= 0 && nr2 < map.length ){
                if(!visit[nr2]){
                    map[nr2] = now.depth+1;
                    visit[nr2] = true;
                    que.add(new Node(nr2,now.depth+1));
                }

            }

            if(nr3 >= 0 && nr3 < map.length){
                if(!visit[nr3]){
                    map[nr3] = now.depth+1;
                    visit[nr3] = true;
                    que.add(new Node(nr3,now.depth+1));
                }
            }
            if(nr == K || nr2 == K || nr3 == K) return;
        }
    }
    private static class Node{
        int idx;
        int depth;
        public Node(int idx, int depth){
            this.idx = idx;
            this.depth = depth;
        }
    }
}