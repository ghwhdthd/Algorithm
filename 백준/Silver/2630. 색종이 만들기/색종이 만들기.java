import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static int whiteCount;
    private static int blueCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=N; j++) {
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }
        find(1,1,N);
        System.out.println(whiteCount);
        System.out.println(blueCount);

    }
    private static void find(int sr,int sc, int n){
        if(check(sr,sc, n)){
            if(map[sr][sc] == 1) blueCount++;
            else whiteCount++;
            return;
        }

        int tmp = n/2;
        find(sr,sc,tmp);
        find(sr+tmp, sc, tmp);
        find(sr, sc+tmp, tmp);
        find(sr+tmp, sc+tmp, tmp);

    }


    private static boolean check(int sr, int sc, int n){

        for (int i = sr; i < sr + n; i++) {
            for (int j = sc; j < sc + n; j++) {
                if(map[i][j] != map[sr][sc]) return false;
            }
        }
        return true;
    }
}