import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static int F;
    private static StringBuilder sb = new StringBuilder();
    private static int[] parent;
    private static int[] network;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        for (int t = 0; t <T ; t++) {



            F = Integer.parseInt(br.readLine());
            Set<String> set =new HashSet<>();
            Map<String,Integer> map = new HashMap<>();
            String[][] inputArr = new String[F][2];
            int index=0;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                inputArr[i][0] = st.nextToken();
                inputArr[i][1] = st.nextToken();
                set.add(inputArr[i][0]);
                set.add(inputArr[i][1]);
                //이름이랑 숫자를 매핑
                if(map.get(inputArr[i][0]) == null){
                    map.put(inputArr[i][0],index);
                    index++;
                }
                if(map.get(inputArr[i][1]) == null){
                    map.put(inputArr[i][1],index);
                    index++;
                }
            }
            int friendNum = set.size();
            parent = new int[friendNum];
            network =new int[friendNum];
            init();
            for (int i = 0; i < F; i++) {
                int a = map.get(inputArr[i][0]);
                int b = map.get(inputArr[i][1]);
                union(a,b);
            }
        }
        System.out.println(sb);
    }

    private static void init() {
        for (int i = 0; i <parent.length ; i++) {
            parent[i]= i;
            network[i] = 1;
        }
    }
    private static void union(int a, int b){
        int aRoot =find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) {
            sb.append(network[aRoot]).append('\n');
            return;
        }
        network[aRoot] = network[aRoot] + network[bRoot];
        parent[bRoot] = aRoot;
        sb.append(network[aRoot]).append('\n');

    }
    private static int find(int x){
        if(x == parent[x]) return x;

        return parent[x]=find(parent[x]);
    }
}