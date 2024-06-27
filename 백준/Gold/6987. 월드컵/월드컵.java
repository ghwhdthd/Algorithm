import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    //조합으로 2팀을 뽑고
    // 2팀의 대전은 승 무 패 중 하나임 -> 이를 dfs로 확인 -> 끝까지 다 확인된 시점에 바로 return

    private static int[] candi;
    private static int[][] matches;
    private static int[][] worldCup;
    private static boolean isEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        candi = new int[6];
        matches = new int[15][2];
        for (int i = 0; i < 6; i++) {
            candi[i] = i;
        }
        worldCup = new int[6][3];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            isEnd = false;
            if(!fill(st)) {
                sb.append(0 + " ");
                continue;
            }
            setMatches();

            dfs(0);
            if(isEnd){
                sb.append(1 + " ");
            }else{
                sb.append(0 + " ");
            }
        }
        System.out.println(sb);

    }

    private static void dfs(int matchNum){
        if(isEnd) return;
        if(matchNum == 15){
            isEnd = true;
            return;
        }

        int aTeam = matches[matchNum][0];
        int bTeam = matches[matchNum][1];

        //승
        if(worldCup[aTeam][0] > 0 && worldCup[bTeam][2] > 0){
            worldCup[aTeam][0]--;
            worldCup[bTeam][2]--;
            dfs(matchNum + 1);
            worldCup[aTeam][0]++;
            worldCup[bTeam][2]++;

        }
        //패
        if(worldCup[aTeam][2] > 0 && worldCup[bTeam][0] > 0){
            worldCup[aTeam][2]--;
            worldCup[bTeam][0]--;
            dfs(matchNum + 1);
            worldCup[aTeam][2]++;
            worldCup[bTeam][0]++;

        }
        //무
        if(worldCup[aTeam][1] > 0 && worldCup[bTeam][1] > 0){
            worldCup[aTeam][1]--;
            worldCup[bTeam][1]--;
            dfs(matchNum + 1);
            worldCup[aTeam][1]++;
            worldCup[bTeam][1]++;
        }

    }
    private static void setMatches(){
        int matchCount = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[matchCount][0] = i;
                matches[matchCount][1] = j;
                matchCount++;
            }
        }
    }


    private static boolean fill(StringTokenizer st ){
        for (int i = 0; i < 6; i++) {
            int w = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            if(w + d + l != 5) return false;
            worldCup[i][0] = w;
            worldCup[i][1] = d;
            worldCup[i][2] = l;
        }
        return true;
    }

}