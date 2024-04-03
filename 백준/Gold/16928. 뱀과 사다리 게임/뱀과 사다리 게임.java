import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static boolean[] visit;
    private static int[] ladder;
    private static int[] snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ladder = new int[101];
        snake = new int[101];
        visit = new boolean[101];
        for (int i = 0; i <=100; i++) {
            snake[i] = -1;
            ladder[i] = -1;
        }
        for (int i = 0; i <N ; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            ladder[from] =to;
        }
        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snake[from] = to;
        }
        bfs();
    }
    private static void bfs(){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(1,0,""));

        while (!que.isEmpty()){
            Node now = que.poll();

            if(now.location == 100){
                System.out.println(now.depth);
                return;
            }

            //주사위 던지기
            for (int i = 1; i <=6 ; i++) {
                int next = now.location + i;
                if(next > 100) continue;
                if(visit[next]) continue;
                visit[next] = true;

                // 사다리에 걸리는 지 확인
                if(ladder[next] != -1){
                    if(!visit[ladder[next]]){
                        visit[ladder[next]] = true;
                        que.add(new Node(ladder[next], now.depth+1,now.s +" "+ now.location));
                    }
                    // 뱀에 걸리는 지 확인
                }else if (snake[next] != -1){
                    if(!visit[snake[next]]){
                        visit[snake[next]] = true;
                        que.add(new Node(snake[next], now.depth+1,now.s +" " + now.location));
                    }

                }else{
                    que.add(new Node(next, now.depth + 1,now.s + " " + now.location));
                }



            }


        }
    }
    private static class Node{
        int location;
        int depth;
        String s;
        public Node(int location, int depth, String s){
            this.location = location;
            this.depth = depth;
            this.s = s;
        }
    }
}