import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long[] inputArr;
    private static int res;
    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inputArr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputArr[i]=Integer.parseInt(st.nextToken());
        }
        //정렬
        Arrays.sort(inputArr);
        
        for (int i = 0; i < N; i++) {
            pro(i);
        }
        System.out.println(res);

    }
    private static void pro(int index){
        int left = 0;
        int right = inputArr.length-1;
        long now = inputArr[index];
        while (true){
            if(left == index) left++;
            if(right== index) right--;
            if(left>=right) return;

            long tmp = inputArr[left] + inputArr[right];

            if(tmp == now){
                res++;
                return;
            }
            if(tmp > now) right--;
            else if(tmp < now) left++;
        }

    }
}