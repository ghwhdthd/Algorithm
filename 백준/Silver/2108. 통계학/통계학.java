import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static int N;
    private static int[] input;
    private static double average;
    private static Map<Integer, Integer> map;

    private static List<Node> li = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());
            input[i] = now;
            average += now;
            if(map.get(now) == null){
                map.put(now, 1);
            }else{
                int tmp = map.get(now);
                map.put(now, tmp+1);
            }
        }

        //평균값
        System.out.println(Math.round(average / N));

        //중앙값
        Arrays.sort(input);
        System.out.println(input[N/2]);

        //최빈값
        for(int key : map.keySet()){
            li.add(new Node(key, map.get(key)));
        }
        Collections.sort(li);
        if(li.size() > 1 && li.get(0).count == li.get(1).count){
            System.out.println(li.get(1).n);
        }else{
            System.out.println(li.get(0).n);
        }

        //범위
        System.out.println(input[N-1] - input[0]);
    }
    private static class Node implements Comparable<Node>{
        int n;
        int count;
        public Node(int n, int count){
            this.n = n;
            this.count = count;
        }
        @Override
        public int compareTo(Node o) {
            if(o.count == count) return n - o.n;
            return o.count - count;
        }
    }
}