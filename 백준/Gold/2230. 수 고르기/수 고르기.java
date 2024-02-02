import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] A;
    private static int N;
    private static int M;
    private static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        //입력 끝

        Arrays.sort(A);
//        System.out.println(Arrays.toString(A));
        int start = 0;
        boolean check = false;
        for (int i = 0; i < N; i++) {

            for (int j = start; j < N; j++) {
                int dif = A[j] - A[i]; // 차이

                // M 보다 차이가 작을때는 다음 A[j]를 봐야함
                if(dif < M) continue;
                // 차이가 M보다 크거나 같을 때 MIN을 최신화해주고 A[++i] 해야함
                start = j;
                if(dif < MIN) MIN = dif;
                if(MIN == M) check = true;
                break;
            }
            if(check) break;
        }
        System.out.println(MIN);

    }

}

