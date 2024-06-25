import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] matches;
    private static int[][] worldCup;
    private static boolean isOk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 6C2 = 15 이므로 총 15경기를 진행
        // 각 경기에 참여하는 팀 숫자 저장
            // 1경기에 1번이랑 2번이 매치했으면 -> matches[1][0] = 1, matches[1][1] = 2
        matches = new int[15][2];
        fillMatches();

        //input
        worldCup = new int[6][3];
        
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            isOk = false;
            st = new StringTokenizer(br.readLine());
            if (!fillWorldCup(st)) {
                System.out.print(0 + " ");
                continue;
            }

            dfs(0);
            
            
            if(isOk){
                System.out.print(1 + " ");
            }else{
                System.out.print(0 + " ");
            }


        }

    }
    private static void dfs(int matchCount){
        if(isOk) return;
        if(matchCount >= 15) {
            isOk = true;
            return;
        }


        int aTeam = matches[matchCount][0];
        int bTeam = matches[matchCount][1];

        //aTeam 승 bTeam 패
        if(worldCup[aTeam][0] > 0 && worldCup[bTeam][2] > 0){
            worldCup[aTeam][0]--;
            worldCup[bTeam][2]--;
            dfs(matchCount+1);

            //원상복귀
            worldCup[aTeam][0]++;
            worldCup[bTeam][2]++;

        }


        //aTeam 패 bTeam 승
        if(worldCup[aTeam][2] > 0 && worldCup[bTeam][0] > 0){
            worldCup[aTeam][2]--;
            worldCup[bTeam][0]--;
            dfs(matchCount+1);

            worldCup[aTeam][2]++;
            worldCup[bTeam][0]++;

        }
        // 무승부
        if(worldCup[aTeam][1] > 0 && worldCup[bTeam][1] > 0){
            worldCup[aTeam][1]--;
            worldCup[bTeam][1]--;
            dfs(matchCount+1);

            worldCup[aTeam][1]++;
            worldCup[bTeam][1]++;
        }



    }

    private static void fillMatches(){
        int matchCount = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[matchCount][0] = i;
                matches[matchCount][1] = j;
                matchCount++;
            }
        }
    }

    private static boolean fillWorldCup(StringTokenizer st ){
        for(int i = 0; i < 6; i++){
            int win = Integer.parseInt(st.nextToken());
            int draw = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());
            worldCup[i][0] = win;
            worldCup[i][1] = draw;
            worldCup[i][2] = lose;

            // 모든 팀은 5번의 경기를 치뤘어야함
            if(win + draw + lose != 5) return false;
        }
        return true;
    }

}