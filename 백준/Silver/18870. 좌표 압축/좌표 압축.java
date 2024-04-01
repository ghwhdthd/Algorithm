import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] point;
    private static int[] sortPoint;
    private static boolean[] visit;
    private static int[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        point = new int[N];
        sortPoint = new int[N];
        visit = new boolean[N];
        res = new int[N];
        //값을 key로 하고 value를 index 리스트로 해서
        Map<Integer, List<Integer>> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
            sortPoint[i] = point[i];

            if(map.get(point[i]) == null){
                List<Integer> indexLi = new ArrayList<>();
                indexLi.add(i);
                map.put(point[i], indexLi);
            }else{
                List<Integer> indexLi = map.get(point[i]);
                indexLi.add(i);
                map.put(point[i],indexLi);
            }
        }

        for (int i = 0; i < N; i++) {
            int before = set.size();
            set.add(point[i]);
            int after = set.size();
            if(before != after) pq.add(point[i]);
        }

        while (!pq.isEmpty()){
            int key = pq.poll();
            int size = pq.size();
            List<Integer> indexLi = map.get(key);
            for(int index : indexLi){
                if(visit[index]) continue;
                res[index] = size;
                visit[index] = true;

            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);


    }

}