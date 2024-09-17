import java.util.*;
class Solution {
    // 다음 포인트로 이동 시 최단 경로가 여러개면 r좌표가 먼저 이동 
        //-> 이거 어떻게 구현하지? 
        //end.r이랑 같아질 때까지 r++ || r-- 를 해서 먼저 이동 시킨 뒤에 c를 이동시키면 됨
    
    // 마지막 포인트에 도달하면 로봇 사라짐
    // 같은 좌표에 로봇이 2대 이상 모이면 위험 상황으로 판단.
    //  - 위험 상황 몇 번 일어나는지 알고 싶음
    //      - 어떤 시간에 여러 좌표에서 위험 상황이 발생한다면 그 횟수를 모두 더함
    //           -> 모든 로봇의 경로를 저장해서 해결해보자
    
    
    private static int[][] points;
    private static int[][] routes;
    private static int[][] board;
    private static List<Integer>[] path;
    
    
    public int solution(int[][] points, int[][] routes) {    
        this.points = points;
        this.routes = routes;
        board = new int[101][101];
        path = new List[routes.length];
        
        for(int i = 0; i < routes.length; i++){
            path[i] = new ArrayList();
        }
        
        
        int n = 1;
        for(int i = 1; i < 101; i++ ){
            for(int j = 1; j < 101; j++){
                board[i][j] = n++;
            }
        }
        
        for(int i = 0; i < routes.length; i++){
            boolean isStart = true;
            for(int j = 0; j < routes[i].length - 1; j++){
                int startP = routes[i][j];
                int endP = routes[i][j+1];
                move(startP, endP, i, isStart);
                isStart = false;
                
            }
        }
        
        
        int maxTime = -1;
        
        for(int i = 0; i < path.length; i++){
            if( maxTime < path[i].size()) maxTime = path[i].size();
            // for(int j = 0; j < path[i].size(); j++){
            //     System.out.print(path[i].get(j) + " ");
            // }
            //System.out.println();
        }
        //System.out.println(maxTime);
        
        int answer = 0;
        Map<Integer, Integer> map;
        for(int j = 0; j < maxTime; j++){
            map = new HashMap();
            for(int i = 0; i < path.length; i++){
                if(path[i].size() -1 < j) continue;
                int pathNum = path[i].get(j);
                if(map.get(pathNum) == null){
                    map.put(pathNum, 1);
                }else{
                    map.put(pathNum, map.get(pathNum) + 1);
                }
                
            }
            for(Integer key : map.keySet()){
                if(map.get(key) >= 2) answer++; 
            }
        }
        
        
        
        return answer;
    }
    private static void move(int startP, int endP, int robot, boolean isStart){
        int nowr = points[startP-1][0];
        int nowc = points[startP-1][1];
        
        //시작 위치 저장, index가 시간임
        if(isStart) path[robot].add(board[nowr][nowc]);
        int er = points[endP-1][0];
        int ec = points[endP-1][1];
        
        
        while(nowr != er){
            if(nowr < er){
                nowr++;
            }else{
                nowr--;
            }
            path[robot].add(board[nowr][nowc]);
        }
        
        
        while(nowc != ec){
            if(nowc < ec){
                nowc++;
            }else{
                nowc--;
            }
            path[robot].add(board[nowr][nowc]);            
        }
        
        
    
    }
    
    private static class Node{
        int r;
        int c;
        int depth;
        String path;
        
        public Node(int r, int c, int depth, String path){
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.path = path;
        }
    }
    
}