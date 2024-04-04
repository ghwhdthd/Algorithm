import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String P;
    private static int N;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) { // T <= 100
            pro();
        }
        System.out.println(sb);
    }
    private static void pro()throws IOException{
        P = br.readLine();
        N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        String inputArr = br.readLine();
        int Rcount = 1;
        String tmp = inputArr.substring(1,inputArr.length()-1);
        StringTokenizer st = new StringTokenizer(tmp,",");
        while (st.hasMoreTokens()){
            int now = Integer.parseInt(st.nextToken());
            deque.add(now);
        }

        for (int i = 0; i < P.length(); i++) { // 100,000
            char func = P.charAt(i);
            if(func == 'R'){
                Rcount++;
            }else{
                if(deque.isEmpty()){
                    sb.append("error").append('\n');
                    return;
                }
                if(Rcount % 2 == 0){
                    //뒤에서 부터 D를 수행
                    deque.removeLast();

                }else{
                    //앞에서 부터 D를 수행
                    deque.removeFirst();
                }
            }
        }
        //함수 끝
        sb.append("[");
        while (!deque.isEmpty()){ // 100,000
            if(Rcount%2 == 0){
                int now = deque.peekLast();
                deque.removeLast();
                sb.append(now);

            }else{
                int now = deque.peekFirst();
                deque.removeFirst();
                sb.append(now);
            }

            if(deque.size()!=0){
                sb.append(",");
            }

        }
        sb.append("]").append('\n');


    }
}