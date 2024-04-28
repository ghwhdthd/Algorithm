import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        List<Integer> lionLocation = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            if(Integer.parseInt(st.nextToken()) == 1){
                lionLocation.add(i);
            }
        }
        if (lionLocation.size() < K) {
            System.out.println(-1);
            return;
        }else{
            for (int i = 0; i <=lionLocation.size() - K; i++) {
                min = Math.min(lionLocation.get(K-1 + i) - lionLocation.get(i),min);
            }
        }
        System.out.println(min+1);

    }
}