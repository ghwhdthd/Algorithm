import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(st.nextToken());
        }
        int r = N-1;
        int l = 0;
        int[] minLR = new int[2];
        long min = 2_000_000_001;
        while (true){
            if(l >= r) break;

            long tmp = Math.abs(input[l] + input[r]);
            if(tmp < min) {
                min = tmp;
                minLR[0] = l;
                minLR[1] = r;
            }
            //2개를 더했을 때 음수면 l++
            //             양수면 r--
            if(input[r] + input[l] == 0){
                minLR[0] = l;
                minLR[1] = r;
                min = 0;
                break;
            }else if(input[r] + input[l] < 0){
                l++;
            }else {
                r--;
            }
        }
        Arrays.sort(minLR);
        System.out.println(input[minLR[0]] + " " + input[minLR[1]]);
    }

}