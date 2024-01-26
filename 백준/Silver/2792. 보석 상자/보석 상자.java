import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    //N : 사람 수, M : 색상 종류
    private static int[] colorArr;
    private static int left = 1;
    private static int right = Integer.MIN_VALUE;
    private static int mid;
    private static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        colorArr = new int[M];
        for (int i = 0; i < M; i++) {
           colorArr[i] = Integer.parseInt(br.readLine());
           if(right < colorArr[i]) right = colorArr[i];
        }


        search();
        System.out.println(MIN);
    }
    private static void search(){
        if(left > right) return;

        int a = 0;
        int b = 0;
        mid = (left + right)/2;
        for (int i = 0; i < colorArr.length; i++) {
            a += colorArr[i]/mid;
            if(colorArr[i]%mid != 0) b+=1;
        }
        if(a + b > N){
            left = mid + 1;
            search();
        }else {
            if(mid < MIN) MIN = mid;
            right = mid - 1;
            search();
        }
    }
}