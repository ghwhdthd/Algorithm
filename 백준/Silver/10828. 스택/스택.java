import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Deque<Integer> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        stack = new ArrayDeque<>();
        for(int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String s = st.nextToken();
            if(s.equals("push")){
                int now = Integer.parseInt(st.nextToken());
                stack.push(now);

            }else if(s.equals("pop")){
                if(stack.isEmpty()){
                    sb.append(-1).append('\n');
                }else{
                    sb.append(stack.pop()).append('\n');
                }
            }else if(s.equals("top")){
                if(stack.isEmpty()){
                    sb.append(-1).append('\n');
                }else{
                    sb.append(stack.peek()).append('\n');
                }
            }else if(s.equals("size")){
                sb.append(stack.size()).append('\n');
            }else if(s.equals("empty")){
                if(stack.isEmpty()){
                    sb.append(1).append('\n');
                }else{
                    sb.append(0).append('\n');
                }
            }else{
                return;
            }
        }
        System.out.println(sb);
    }
}