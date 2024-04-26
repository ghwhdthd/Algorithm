import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] dr = {2, 1, -1};
    private static int N, K;
    private static boolean[] visit;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[100_001];
        bfs();
        System.out.println(min);

    }
    private static void bfs(){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(N,0));
        while (!que.isEmpty()){
            Node now = que.poll();
            visit[now.index] = true;
            if(now.index == K){
                min = Math.min(min, now.depth);
            }
            for(int i = 0; i < 3; i++){
                int nr = 0;
                int depth = now.depth;
                if(i == 0){
                    nr = now.index * dr[i];
                }else{
                    nr = now.index + dr[i];
                    depth ++;
                }
                if(nr < 0 || nr > 100_000) continue;
                if(visit[nr]) continue;
                que.add(new Node(nr, depth));
            }

        }

    }
    private static class Node{
        int index;
        int depth;
        public Node(int index, int depth){
            this.index = index;
            this.depth = depth;
        }
    }

}