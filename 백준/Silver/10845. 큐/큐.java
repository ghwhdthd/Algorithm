import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Deque<Integer> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        que = new ArrayDeque<>();
        for(int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String s = st.nextToken();
            if(s.equals("push")){
                int now = Integer.parseInt(st.nextToken());
                que.add(now);

            }else if(s.equals("pop")){
                if(que.isEmpty()){
                    sb.append(-1).append('\n');
                }else{
                    sb.append(que.poll()).append('\n');
                }
            }else if(s.equals("size")){
                sb.append(que.size()).append('\n');
            }else if(s.equals("empty")){
                if(que.isEmpty()){
                    sb.append(1).append('\n');
                }else{
                    sb.append(0).append('\n');
                }
            }else if(s.equals("front")) {
                if (que.isEmpty()) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(que.peekFirst()).append('\n');
                }
            }else{
                if (que.isEmpty()) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(que.peekLast()).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}