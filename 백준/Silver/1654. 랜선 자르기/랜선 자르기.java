import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int K,N;
    private static int[] lan;
    private static int maxLan;
    private static long max = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lan = new int[N];
        maxLan = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            if(lan[i] > maxLan){
                maxLan = lan[i];
            }
        }
        bSearch();
        System.out.println(max);

    }
    private static void bSearch(){
        long r = maxLan;
        long l = 1;
        while (true){
            if(l > r) break;
            long mid = (r + l) / 2;
            long c = count(mid);
            if(c < N){
                r = mid - 1;
            }else{
                l = mid + 1;
                if(mid > max) max = mid;
            }

        }
    }
    private static long count(long n ){
        long count = 0;
        for (int i = 0; i < N; i++) {
            count += lan[i] / n;
        }
        return count;
    }

}