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
    private static List<List<Integer>> li;
    private static int[] inDegree; //진입차수 배열
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        li = new ArrayList<>();
        //진입차수 배열
        inDegree = new int[N+1]; // 1부터 시작
        for (int i = 0; i <=N; i++) {
            li.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            //a -> b
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            li.get(a).add(b);
            //b의 진입차수++
            inDegree[b]++;
        }

        tsort();

        System.out.println(sb);

    }
    private static void tsort(){
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            //진입차수가 0이면 q에 추가
            if(inDegree[i] == 0){
                q.offer(i);
                sb.append(i).append(" ");
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            //now 기준으로 next의 진입차수를 --
            List<Integer> nextList = li.get(now);
            for (int i = 0; i < nextList.size(); i++) {
                int next = nextList.get(i);
                inDegree[next]--;
                if(inDegree[next] == 0){
                    q.offer(next);
                    sb.append(next).append(" ");
                }
            }
        }
    }
}