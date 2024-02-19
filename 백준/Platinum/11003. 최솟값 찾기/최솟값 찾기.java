import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N,L;
    private static int[] input;
    private static StringBuilder sb = new StringBuilder();
    private static Deque<Node> deque = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        input = new int[N];
        st= new StringTokenizer(br.readLine());
        for (int i = 0; i <N; i++) {
            input[i]=Integer.parseInt(st.nextToken());
            find(i);
        }

        System.out.println(sb);
    }
    private static void find(int start){
        start= start -L + 1;
        int end = start +L -1;
        int now = input[end];
        //dq가 비어있는 경우에만 pq 채우기
        if(deque.isEmpty()){
            deque.offer(new Node(now,end));
        }else{
            //새로 들어올 값보다 보다 큰 이전 값들은 다 빼기
            while (!deque.isEmpty() && deque.peekLast().n > now){
                deque.pollLast();
            }
            //새로운 값 넣기
            deque.offer(new Node(now,end));
            //첫번째 값이 start 범위보다 작으면 빼기
            if(deque.peekFirst().index < start)deque.pollFirst();
        }
        
        //맨 앞에 있는 값 출력
        sb.append(deque.peekFirst().n).append(" ");
    }
    private static class Node {
        int n;
        int index;
        public Node(int n, int index){
            this.n = n;
            this.index = index;
        }
    }
}