import java.util.*;
class Solution {
    private static int[] parent;
    private static int res;
    
    
    public int solution(int n, int[][] computers) {
        res = n;
        int answer = 0;
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                if(computers[i][j] == 1){
                    union(i, j);
                }
                
            }
        }
        
        return res;
    }
    private static int find(int x){
        if(parent[x] == x) return x;
        
        return parent[x] = find(parent[x]);
        
    }
    
    private static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return;
        
        res--;
        parent[bRoot] = aRoot;
        
    }
}