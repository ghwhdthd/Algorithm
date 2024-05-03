import java.util.*;
class Solution {
    private static int[] parents;
    private static int answer;
    
    public int solution(int n, int[][] costs) {
        answer = 0;
        init(n);
        cal(n, costs);
        
        return answer;
    }
    
    private static void cal(int n, int[][] costs){
        PriorityQueue<Node> pq = new PriorityQueue();
        for(int i = 0; i < costs.length; i++){
            int a = costs[i][0];
            int b = costs[i][1];
            int w = costs[i][2];
            pq.add(new Node(a, b, w));
        }
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            union(now.a, now.b, now.w);
        }
    }
    
    private static void init(int n){
        parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
    }
    
    private static int find(int x){
        if(x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
    
    private static void union(int a, int b, int w){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;
        
        parents[bRoot] = aRoot;
        answer += w;
        
    }
        
    private static class Node implements Comparable<Node>{
        int a;
        int b;
        int w;
        public Node(int a, int b, int w){
            this.a = a;
            this.b = b;
            this.w = w;
        }
        @Override
        public int compareTo(Node o){
            return w - o.w;
        }
        
    }
}