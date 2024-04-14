import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static boolean[] visit;
    private  static List<List<Integer>> li;
    private static int[][] output;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /**
         0,1
         1,2
         2,0
                        1 -> 6
         0 -> 3 -> 5 -> 6 -> 2
              3 -> 4 -> 0
         */
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        output = new int[N][N];
        li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            li.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int from = i;
                int to = j;
                int check = Integer.parseInt(st.nextToken());
                if(check == 1){
                    li.get(from).add(to);
               }
            }
        }

        for (int i = 0; i < N; i++) {
            bfs(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(output[i][j]+ " ");
            }
            System.out.println();
        }
    }
    private static void bfs(int start){
        Deque<Integer> que = new ArrayDeque<>();
        que.add(start);
        while(!que.isEmpty()){
            Integer now = que.poll();
            for(Integer next : li.get(now)){
                if(output[start][next] == 1) continue;
                output[start][next] = 1;
                que.add(next);
            }
        }
    }
}