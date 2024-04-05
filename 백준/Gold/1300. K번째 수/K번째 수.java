import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static int N;
    private static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        long low = 1;
        long high = K;

        // 1열은 1의 배수
        // 2열은 2의 배수
        // 3열은 3의 배수
        // 4열은 4의 배수
        //  각 열은 N개의 배수까지 존재함
        //      예를 들어 N == 4인 경우
        //          1*1, 1*2, 1*3, 1*4
        //          2*1, 2*2, 2*3, 3*4
        //          3*1, 3*2, 3*3, 3*4
        //          4*1, 4*2, 4*3, 4*4
        // K번째 오는 숫자를 구하려면
        //  1의 배수에서 mid 보다 작은거나 같은게 몇개인지
        //  2의 배수에서 mid 보다 작은거나 같은게 몇개인지
        //  3의 배수에서 mid 보다 작은거나 같은게 몇개인지
        //  4의 배수에서 mid 보다 작은거나 같은게 몇개인지
        //  구해서 합한 개수가 K 인 경우 해당 mid가 정답임.

        while (true){
            if(low > high) break;
            long count =0;
            long mid = (low+high)/2;
            for (int i = 1; i <=N ; i++) {
                //i는 1의 배수, 2의 배수 ... 을 의미함.

                long tmpCount =  (mid / i);
                if(tmpCount > N ) tmpCount = N;

                count+=tmpCount;
            }

            if(count >= K) high = mid-1;
            else low = mid + 1;
        }
        System.out.println(low);

    }

}