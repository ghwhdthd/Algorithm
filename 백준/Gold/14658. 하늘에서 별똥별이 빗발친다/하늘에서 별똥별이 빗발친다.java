import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N,M,L,K;
    private static List<int[]> node;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// c
        M = Integer.parseInt(st.nextToken());// r
        L = Integer.parseInt(st.nextToken());// 트램펄린 길이
        K = Integer.parseInt(st.nextToken());// 별똥별 개수
        node = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            node.add(new int[]{x,y});
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            int[] now = node.get(i);
            int nowx = now[0]; // 현재 별똥별 좌표
            int nowy = now[1];

            for (int j = nowx-L; j <= nowx; j++) { // j,nowx은 트랜펄린의 좌상단 꼭지점
                if(j < 0) continue;
                int cnt =0;
                for (int k = 0; k < K; k++) {
                    int[] tmp= node.get(k);
                    if(tmp[0] >= j && tmp[0] <= j+L && tmp[1]>= nowy && tmp[1] <= nowy+L) cnt++;
                }
                res = Math.max(cnt,res);
            }
        }

        System.out.println(K -res);
    }
}