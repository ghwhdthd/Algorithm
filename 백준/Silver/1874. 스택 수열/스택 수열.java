import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static int N;
    private static int[] input;
    private static Deque<Integer> stack;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        stack = new ArrayDeque<>();
        int start = 1;
        int end = 0;
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            end = input[i];
            int tmp = pro(start, end, input[i]);

            if (tmp == -2) {
                System.out.println("NO");
                return;
            }else{
                if(start < tmp) start = tmp;
            }

        }
        System.out.println(sb);

    }
    private static int pro(int start ,int end, int input){
        //stack peek 했을 때 input 보다 작으면 안되는 거임
        //

        for (int i = start; i <= end; i++) {
            stack.push(i);
            sb.append("+").append('\n');
        }

        if(!stack.isEmpty()){
            if(input < stack.peek()){
                return -2;
            }else{
                stack.pop();
                sb.append("-").append('\n');
            }
        }
        return end+1;
    }


}