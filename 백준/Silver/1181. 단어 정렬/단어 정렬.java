import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        N = Integer.parseInt(br.readLine());
        List<Node> li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int before = set.size();
            set.add(str);
            int after = set.size();
            if(before == after) continue;
            li.add(new Node(str));
        }

        Collections.sort(li);
        for (int i = 0; i < li.size(); i++) {
            sb.append(li.get(i).s).append('\n');
        }
        System.out.println(sb);


    }
    private static class Node implements Comparable<Node>{
        String s;
        public Node(String s){
            this.s = s;
        }
        //길이가 짧은 순서로
        // 길이가 같을 때는 사전 순으로
        public int compareTo(Node o){
            if(s.length() == o.s.length()){
                return s.compareTo(o.s);
            }else{
                return s.length() - o.s.length();
            }
        }
    }

}