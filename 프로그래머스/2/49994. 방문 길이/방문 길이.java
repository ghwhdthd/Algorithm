import java.util.*;
class Solution {
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static boolean[][][] visit;
    public int solution(String dirs) {
        int answer = 0;
        visit = new boolean[11][11][4];
        int nowr = 5;
        int nowc = 5;
        int nr = 5;
        int nc = 5;
        for(int i = 0; i < dirs.length(); i++){
            char func = dirs.charAt(i);
            int dir = 0;
            int back = 0;
            if(func == 'U'){
                dir = 0;
                back = 1;
            }
            else if(func == 'D'){
                dir = 1;
                back = 0;
            }
            else if(func == 'L'){
                dir = 2;
                back = 3;
            }
            else{
                dir = 3;
                back = 2;
            }
            nr = nowr + dr[dir];
            nc = nowc + dc[dir];
            if(nr < 0 || nc < 0 || nr > 10 || nc > 10) continue;
            
            if(!visit[nr][nc][dir]) {
                answer++;
            }
            visit[nr][nc][dir] = true;
            visit[nowr][nowc][back] = true;
            // else{
            //     System.out.println(nowr + ", " +nowc +" -> " + nr+", "+nc);
            //     System.out.println("now : "+visit[nowr][nowc] + ",  n : " +visit[nr][nc]);
                
            // }
            
            nowr = nr;
            nowc = nc;
        }
        return answer;
    }
}