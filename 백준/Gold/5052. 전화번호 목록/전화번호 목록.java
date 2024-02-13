

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringBuilder sb;
    private static int N;
    private static String[] arr;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i <T ; i++) {
            pro();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
    private static void pro()throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        Arrays.sort(arr);
        boolean res = true;
        for (int i = 1; i < N; i++) {
            if(arr[i].startsWith(arr[i-1])) {
                res = false;
                break;
            }
        }
        if(res){
            sb.append("YES\n");
        }else{
            sb.append("NO\n");
        }


    }
}
