import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N,K;
    private static int[] D;
    private static List<List<Integer>> order;
    private static List<List<Integer>> reverseOrder;
    private static int[] inDegree;
    private static int W;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            pro();
        }
    }

    private static void pro() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 건물 짓는데 걸리는 시간
        D = new int[N+1]; //1부터 시작
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }

        //각 노드의 진입차수 저장
        inDegree = new int[N+1]; //1부터 시작

        //건설 순서 연결리스트
        order = new ArrayList<>();

        //이전에 어떤 건물이 건설되어야 하는지 알 수 있는 리스트
        reverseOrder = new ArrayList<>();
        //초기화
        for (int i = 0; i <=N; i++) {
            order.add(new ArrayList<>());
            reverseOrder.add(new ArrayList<>());
        }
        //건설 순서
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            //a -> b
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //순서대로 연결
            order.get(a).add(b);

            //반대로 연결
            reverseOrder.get(b).add(a);

            //b의 진입차수++
            inDegree[b]++;
        }
        //목표 건물
        W = Integer.parseInt(br.readLine());

        //dp[7] 이면 7번 건물까지 짓는데 걸리는 시간
        dp = new int[N+1];


        //위상정렬
        tSort();


        System.out.println(dp[W]);

    }

    private static void tSort(){
        Deque<Integer> q = new ArrayDeque();
        for (int i = 1; i <=N ; i++) {
            //진입차수가 0이면 q에 넣기
            if(inDegree[i] == 0) {
                q.offer(i);
                //처음에는 무조건 자기 자신만큼만 시간이 걸림 
                dp[i] = D[i];
            }
        }
        //q가 빌때까지 정렬시작
        while (!q.isEmpty()){
            int now = q.poll(); // 진입차수가 0인걸 보장함.

            //now와 연결되어있는 다음 노드들
            List<Integer> nextList = order.get(now);
            //now와 연결되어있는 이전 노드들
            List<Integer> preList = reverseOrder.get(now);
            int max = 0;
            //dp[] 을 통해 이전에 가장 큰 시간이 걸린 건물이 몇시간인지 알아냄
            for (int i = 0; i < preList.size(); i++) {
                int pre =preList.get(i);
                max = Math.max(dp[pre], max);
            }
            //dp[now] = 현재 건물짓는 시간 + 이전에 가장 큰 시간이 걸린 건물시간
            dp[now] = D[now] + max;

            //정답이 나왔으면 break
            if(now == W ) break;
            
            //기본 위상정렬 코드
            for (int i = 0; i < nextList.size(); i++) {
                int next = nextList.get(i);
                //next의 진입차수--
                inDegree[next]--;
                //next의 진입차수가 0이 되면 q에 추가
                if(inDegree[next] == 0){
                    q.offer(next);
                }
            }
        }

    }
}