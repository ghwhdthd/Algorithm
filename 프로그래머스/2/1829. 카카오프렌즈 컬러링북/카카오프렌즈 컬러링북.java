import java.util.*;
class Solution {
    private static int[] dr = {0,0,-1,1};
    private static int[] dc = {1,-1,0,0};
    private static int[][] visit;
    private static int color;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visit = new int[m][n];
        
        
        
        for(int i=0; i<picture.length; i++){
            for(int j=0; j<picture[i].length; j++){
                if(picture[i][j] == 0) continue;
                if(visit[i][j] == 1) continue;
                color = 0;
                numberOfArea++;
                bfs(i,j,picture[i][j], m, n, picture);
                if(color > maxSizeOfOneArea) maxSizeOfOneArea = color;    
            }
        }
        
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    private static void bfs(int r, int c, int kind, int m, int n, int[][] picture){
        Deque<Node> que = new ArrayDeque();
        visit[r][c] = 1;
        color++;
        que.add(new Node(r,c,kind));
        
        while(!que.isEmpty()){
            Node now = que.poll();
            
            for(int i=0; i<4; i++){
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;
                if(nr < 0 || nc < 0 || nr>= m || nc >= n) continue;
                if(visit[nr][nc]==1) continue;
                if(picture[nr][nc] != now.kind) continue;
                visit[nr][nc] = 1;
                color++;
                que.add(new Node(nr,nc,picture[nr][nc]));
                
            }
        }
        
    }
    static class Node{
        int r;
        int c;
        int kind;
        public Node(int r,int c, int kind){
            this.r= r;
            this.c =c;
            this.kind =kind;
        }
    }
}