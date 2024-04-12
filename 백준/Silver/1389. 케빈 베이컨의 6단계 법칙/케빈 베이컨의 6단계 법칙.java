import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static List<List<Integer>> friends;
    private static int [] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends =  new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            friends.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }
        PriorityQueue<Res> pq = new PriorityQueue<>();
        for (int i = 1; i <=N; i++) {
            visit = new int[N+1];
            bfs(i);
            int tmp = 0;
            for (int j = 1; j < visit.length; j++) {
                tmp += (visit[j]-1);
            }
            pq.add(new Res(i,tmp));
        }
        System.out.println(pq.poll().num);


    }
    private static void bfs(int n){
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(n,1));
        visit[n] = 1;
        while (!que.isEmpty()){
            Node now = que.poll();
            for(int next : friends.get(now.n)){
                if(visit[next] != 0) continue;
                visit[next] = now.depth+1;
                que.add(new Node(next,now.depth+1));

            }
        }
    }
    private static class Res implements Comparable<Res>{
        int num;
        int kevinNum;
        public Res(int num, int kevinNum){
            this.num = num;
            this.kevinNum = kevinNum;
        }
        public int compareTo(Res o){
            if(kevinNum == o.kevinNum) return num - o.num;
            return kevinNum - o.kevinNum;
        }

    }
    private static class Node{
        int n;
        int depth;
        public Node(int n, int depth){
            this.n = n;
            this.depth = depth;
        }
    }
}