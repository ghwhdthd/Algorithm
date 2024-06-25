import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[][] input;
    private static List<Node> chickens;
    private static List<Node> homes;
    private static int[] output;
    private static int N, M;
    private static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chickens = new ArrayList<>();
        homes = new ArrayList<>();

        input = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if(input[i][j] == 1){
                    homes.add(new Node(i, j));
                }else if (input[i][j] == 2){
                    chickens.add(new Node(i, j));
                }
            }
        }

        output = new int[M];

        //조합 치킨개수Cm
        dfs(0, 0);
        System.out.println(ans);

    }
    private static void dfs(int start, int depth){
        if(depth >= M){
            //치킨집과 집과의 거리 구하기
            int res = 0;

            for (int i = 0; i < homes.size(); i++) {
                Node home = homes.get(i);
                int tmp = Integer.MAX_VALUE;
                for (int j = 0; j < output.length; j++) {
                    Node chicken = chickens.get(output[j]);
                    int now = cal(chicken, home);
                    tmp = Math.min(tmp, now);
                }
                res+=tmp;
            }


            ans = Math.min(res, ans);

            return;
        }

        //조합
        for (int i = start; i < chickens.size(); i++) {
            //현재 치킨집 선택
            output[depth] = i;
            dfs(i + 1, depth + 1);


        }
    }
    private static int cal (Node chicken, Node home){
        return Math.abs(chicken.r - home.r) + Math.abs(chicken.c - home.c);
    }

    private static class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}