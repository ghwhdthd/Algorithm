import java.util.*;
class Solution {
    static int N,M;
    static int[][] visit;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static int MAX = 0;
    static Map<Integer, Integer> map;
    static boolean[] v;
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        map = new HashMap<>();
        int tmp = 1;
        visit = new int[N][M];
        
        for (int c = 0; c < M; c++) {
            for (int r = 0; r < N; r++) {
                int now = 0;
                if(land[r][c] == 0) continue;
                if(visit[r][c]!=0) continue;
                
                now = bfs(r,c,land,tmp);
                map.put(tmp,now);
                tmp++;
                
            }
        }
//         for(Integer key : map.keySet()){

//             System.out.println(key +" " + map.get(key));
//         }
        
        
        for (int c = 0; c < M; c++) {
            int sum =0;
            v = new boolean[map.size()];
            for (int r = 0; r < N; r++) {
                if(land[r][c] == 0) continue;
                if(visit[r][c]!=0){
                    if(v[visit[r][c]-1]) continue;
                    v[visit[r][c]-1] = true;
                    sum+=map.get(visit[r][c]);
                    
                }
            }
            if(MAX < sum){
                MAX = sum;
            }
            
        }
        return MAX;
    }
    
    static int bfs(int r, int c, int[][] land,int tmp){
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(r,c));
        visit[r][c] = tmp;
        int cnt = 1;
        while (!q.isEmpty()){
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + now.r;
                int nc = dc[i] + now.c;

                if(nr < 0 || nc < 0 || nr >=N || nc >= M) continue;
                if(visit[nr][nc]!=0) continue;
                if(land[nr][nc] == 0) continue;
                visit[nr][nc] = tmp;
                q.add(new Node(nr,nc));
                cnt++;
                
            }

        }
        return cnt;
    }
    static class Node{
        int r;
        int c;

        public Node(int r,int c){
            this.r= r;
            this.c = c;
        }
    }

}