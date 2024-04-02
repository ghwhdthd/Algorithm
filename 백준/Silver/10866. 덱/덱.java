import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String fuc = st.nextToken();
            if(fuc.equals("push_front")){
                int n = Integer.parseInt(st.nextToken());
                deque.addFirst(n);
            }else if(fuc.equals("push_back")){
                int n = Integer.parseInt(st.nextToken());
                deque.addLast(n);
            }else if(fuc.equals("pop_front")){
                if(deque.isEmpty()){
                    sb.append("-1").append('\n');
                }else{
                    sb.append(deque.peekFirst()).append('\n');
                    deque.removeFirst();
                }
            }else if(fuc.equals("pop_back")){
                if(deque.isEmpty()){
                    sb.append("-1").append('\n');
                }else{
                    sb.append(deque.peekLast()).append('\n');
                    deque.removeLast();
                }
            }else if(fuc.equals("size")){
                sb.append(deque.size()).append('\n');
            }else if(fuc.equals("empty")){
                if(deque.isEmpty()){
                    sb.append("1").append('\n');
                }else{
                    sb.append("0").append('\n');
                }
            }else if(fuc.equals("front")){
                if(deque.isEmpty()){
                    sb.append("-1").append('\n');
                }else{
                    sb.append(deque.peekFirst()).append('\n');
                }
            }else if(fuc.equals("back")){
                if(deque.isEmpty()){
                    sb.append("-1").append('\n');
                }else{
                    sb.append(deque.peekLast()).append('\n');
                }
            }
        }
        System.out.print(sb);
    }
}