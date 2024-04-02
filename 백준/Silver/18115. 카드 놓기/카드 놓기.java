import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main {

    private static int N;
    private static int[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        
        int n = 0;
        for (int i = N-1; i >= 0 ; i--) {
            int number = input[i];
            n++;
            if(number == 1){
                deque.push(n);

            }else if(number == 2){
                int tmp = deque.pop();
                deque.push(n);
                deque.push(tmp);

            }else{
                deque.addLast(n);
            }
        }
        while (!deque.isEmpty()){
            sb.append(deque.pop()).append(" ");
        }
        System.out.println(sb);

    }

}