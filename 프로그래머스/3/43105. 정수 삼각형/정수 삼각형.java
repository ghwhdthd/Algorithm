import java.util.*;

class Solution {
    private static int[] dr = {-1, -1};
    private static int[] dc = {-1, 0};
    private static int MAX = -1;
    public int solution(int[][] triangle) {
        int answer = 0;
        for(int i = 1; i < triangle.length; i++){
            for(int j = 0; j < triangle[i].length; j ++){
                int now = triangle[i][j];
                int tmpMax = -1;
                for(int k = 0; k < 2; k++){
                    int br = i + dr[k];
                    int bc = j + dc[k];
                    if(br < 0 || bc < 0 || br >= triangle.length || bc >= triangle[i-1].length) continue;                     
                    int tmp = triangle[br][bc] + now;
                    tmpMax = Math.max(tmpMax, tmp);
                    
                }
                triangle[i][j] = tmpMax;
                MAX = Math.max(tmpMax, MAX);
            }
        }
        
        return MAX;
    }
}