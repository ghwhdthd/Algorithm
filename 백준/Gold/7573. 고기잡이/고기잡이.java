import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N,I,M;
    private static List<int[]> gmul;
    private static List<int[]> fishes;
    private static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 모눈종이 크기
        I = Integer.parseInt(st.nextToken()); // 그물 길이
        M = Integer.parseInt(st.nextToken()); // 물고기 수
        // 물고기 위치 배열 완성
        fishes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            fishes.add(new int[]{r,c});
        }

        gmul = new ArrayList<>();
        //그물 종류 완성
        for (int i = 1; i < I/2; i++) {
            int tmp = I-i*2;
            if(tmp%2 == 0){
                gmul.add(new int[]{i,tmp/2});
            }
        }

        for (int i = 0; i < M; i++) {
            int[] now = fishes.get(i);
            int nowr = now[0];
            int nowc = now[1];
            for (int j = 0; j < gmul.size(); j++) {
                int[] nowGmul =gmul.get(j);
                int garo = nowGmul[0];
                int sero = nowGmul[1];

                //그물 놓기
                for (int k = nowc-garo; k <=nowc; k++) {// k,nowr 이 그물의 좌상단
                    if(k <= 0) continue;
                    //물고기 몇마리인지 세기
                    int cnt =0;
                    for (int l = 0; l < fishes.size(); l++) {
                        int fishR = fishes.get(l)[0];
                        int fishC = fishes.get(l)[1];
                        //물고기 위치가 현재 그물안에 있으면 cnt++
                        if(fishC >= k && fishC <= k+garo && fishR >=nowr && fishR <= nowr + sero ) cnt++;
                    }
                    MAX = Math.max(MAX, cnt);
                }
            }
        }

        System.out.println(MAX);
    }


}