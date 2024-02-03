import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] dpTable;
    static int[] pc = {-2,-1,1,2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());

        arr = new int[N][3];
        dpTable = new int[N][3];

        for(int r=0; r<N; r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c=0; c<3; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //첫번째 줄 채우기
        for(int c=0; c<3; c++){
            dpTable[0][c] = arr[0][c];
        }
        for(int r=1; r<N; r++){
            for(int c=0; c<3; c++) {
                List<Integer> li = new ArrayList<>();
                for (int i = 0; i < pc.length; i++) {
                    int tmpc = pc[i] + c;
                    if(tmpc <0 || tmpc>2) continue;
                    li.add(dpTable[r-1][tmpc] + arr[r][c]);
                }
                dpTable[r][c] = Collections.min(li);
            }
        }

        int lastMin= Integer.MAX_VALUE;
        for(int c=0; c<3; c++){
            if(lastMin > dpTable[N-1][c]) lastMin=dpTable[N-1][c];
        }
        System.out.println(lastMin);
    }
}