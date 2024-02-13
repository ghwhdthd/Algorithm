import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static long[] arr;
    private static long[] sumDp;
    private static long[] cnt;
    private static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        sumDp = new long[N+1]; // 누적합
        //M이 3인 경우 나머지는 0,1,2가 될 수 있음
        // cnt[0] 은 나머지가 0인 개수를 의미함
        cnt = new long[M]; //
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sumDp[i] = arr[i] + sumDp[i-1]; // 1~i 까지의 누적합

            if(sumDp[i]%M == 0) res++; // 1~i까지의 구간이 M으로 나누어떨어지면 res++

            cnt[(int)(sumDp[i]%M)]++; // 나머지가 같은게 몇개인지 찾아야하니까 저장
        }
        // 구간합은 sumDp[to] - sumDp[from -1] 임
        // 구간의 합이 M으로 나누어 떨어지는 구간의 개수 구하는게 목적
        // 즉 (sumDp[to] - sumDp[from-1]) % M == 0 이 되는 걸 찾는거임
        // sumDp[to] % M == sumDp[from-1] % M  으로 풀어쓸 수 있음
        // 위에서 to == i이고 from==1 인 경우는 찾아서 res에 더했음
        // 이제 from!=1인 경우만 찾으면 됨
        // 즉, cnt[i](i가 나머지인 인덱스의 수)에서 2가지를 뽑는 경우의 수 카운팅한다.

        for (int i = 0; i <cnt.length ; i++) {
            long n = cnt[i];
            if(n < 2) continue;
            long tmp = (n*(n-1))/2;
            res += tmp;
        }

        System.out.println(res);
    }
}