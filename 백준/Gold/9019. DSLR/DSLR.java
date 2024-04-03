import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int A, B;
    private static boolean [] visit;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            pro();
        }
    }
    private static void pro()throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        visit = new boolean[10_000];

        bfs();
    }
    private static void bfs(){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(A,""));
        visit[A] = true;
        while (!que.isEmpty()){
            Node now = que.poll();
            if(now.n == B){
                System.out.println(now.s);
                break;
            }
            int d = D(now.n);
            if(!visit[d]){
                visit[d] = true;
                que.add(new Node(d,now.s + "D"));
            }
            int s = S(now.n);
            if(!visit[s]){
                visit[s] = true;
                que.add(new Node(s,now.s + "S"));
            }
            int l = L(now.n);
            if(!visit[l]){
                visit[l] = true;
                que.add(new Node(l,now.s + "L"));
            }
            int r = R(now.n);
            if(!visit[r]){
                visit[r] = true;
                que.add(new Node(r,now.s + "R"));
            }


        }
    }
    private static int D(int now){
        return (now * 2) % 10_000;
    }

    private static int S(int now){
        int next = now -1;
        if(next < 0) return 9999;
        return next;
    }
    private static int L(int now){
        if(now == 0) return 0;
        int n = now / 1000;
        int next = (now % 1000 *10) + n;
        return next;

    }
    private static int R(int now){
        if(now == 0) return 0;
        int n = now % 10;
        int next = now/10 + 1000 * n;
        return next;
    }
    private static class Node{
        int n;
        String s;

        public Node(int n, String s){
            this.n = n;
            this.s = s;
        }

    }
}