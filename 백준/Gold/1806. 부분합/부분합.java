import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,S;
    private static int[] inputArr;
    private static int[] sumArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        inputArr = new int[N+1];
        sumArr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
           inputArr[i] = Integer.parseInt(st.nextToken());
           sumArr[i] = inputArr[i] + sumArr[i-1];
        }
        int left = 0;
        int right = 1;
        int cnt = 0;
        int min = Integer.MAX_VALUE;

        boolean check = false;
        while (true){
            if(right > N) break;
            int tmp = sumArr[right]- sumArr[left];

            if(tmp >= S) {
                check = true;
                cnt = right - left;
                min = Math.min(cnt,min);
                left++;
            }
            else right++;

        }
        if (!check) {
            System.out.println(0);
        }else{
            System.out.println(min);
        }
    }

}