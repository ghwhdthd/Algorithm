import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        List<String> li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            int before = set.size();
            set.add(s);
            int after = set.size();
            if(before == after) li.add(s);
        }
        System.out.println(li.size());
        Collections.sort(li);
        for(String s: li){
            System.out.println(s);
        }
    }

}