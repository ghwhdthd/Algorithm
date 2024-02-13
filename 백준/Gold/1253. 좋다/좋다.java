

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static int res =0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] =Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i <N; i++) {
            int now = arr[i];
            int left = 0;
            int right = N-1;

            while (true){
                if(i == left) left++;
                if(i == right) right --;
                if(right == left) break;



                int tmp = arr[left] + arr[right];
                if(tmp == now){
                    //같은 경우 res++
                    res++;
                    break;
                }

                if(tmp > now){
                    //크면 right --
                    right--;
                }else if(tmp < now){
                    //작으면 left ++
                    left++;
                }


            }

        }

        System.out.println(res);



    }
}
