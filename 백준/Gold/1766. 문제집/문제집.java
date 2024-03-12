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
    private static int[] inDegree;
    private static List<List<Integer>> list;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree = new int[N+1];//1부터
        list = new ArrayList<>();

        for (int i = 0; i < N+1; i++) {
            list.add(new ArrayList());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            //진입차수 ++
            inDegree[b]++;
        }

        topology();
        System.out.println(sb);
    }

    private static void topology() {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 1; i <inDegree.length ; i++) {
            if(inDegree[i] == 0){
                que.add(i);
            }
        }

        while (!que.isEmpty()){
            int now = que.poll();

            for (int i = 0; i < list.get(now).size(); i++) {
                inDegree[list.get(now).get(i)]--;
                if(inDegree[list.get(now).get(i)] == 0) que.add(list.get(now).get(i));
            }

            sb.append(now).append(" ");
        }
    }
}