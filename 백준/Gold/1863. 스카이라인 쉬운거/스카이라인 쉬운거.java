import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] xyArr;
    private static List<Integer> zeroIndex;
    private static boolean[] check;
    private static int max = -1;
    private static int[] res;
    private static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        zeroIndex = new ArrayList<>();
        xyArr = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            xyArr[i][0] = x;
            xyArr[i][1] = y;
            if(y == 0) zeroIndex.add(i);
            if(y>max) max = y;
        }
        res = new int[max+1];

        pro();
        for (int i = 0; i < res.length; i++) {
            cnt += res[i];
        }
        System.out.println(cnt);
    }

    private static void pro() {
        check = new boolean[max+1];
        
        for (int i = 0; i < N; i++) {
            int x = xyArr[i][0];
            int y = xyArr[i][1];
            


            //y보다 큰게 이전에 나왔었는지 검사
            for (int j = y+1; j <=max; j++) {
                if(check[j]){
                    res[j] ++;
                    check[j] = false;
                }
            }
            if(y == 0) {
                check = new boolean[max + 1];
            }else{
                check[y] = true;
            }

        }
        for (int i = 0; i < check.length; i++) {
            if(check[i]){
                res[i]++;
            }
        }

    }


}