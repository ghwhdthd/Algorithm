import java.util.*;
class Solution {
    // 투포인터로 풀면 됨
    private static PriorityQueue<Node> pq;
    
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        pq = new PriorityQueue();
        int r = 0;
        int l = 0;
        int now = sequence[0];
        
        while(r <= sequence.length){
            if(l > r) break;
            if (now <= k){
                if(now == k) pq.add(new Node(r, l, r-l));
                r++;
                if(r >= sequence.length) break;
                now += sequence[r];
            }else{
                now -= sequence[l];
                l++;
            }
            
        }
        
        
        Node res = pq.poll();
        
        answer[0] = res.l;
        answer[1] = res.r; 
        return answer;
    }
    private static class Node implements Comparable<Node>{
        int r;
        int l;
        int dif;
        public Node(int r, int l, int dif){
            this.r = r;
            this.l = l;
            this.dif = dif;
        }
        
        public int compareTo(Node o){
            if(this.dif == o.dif) return this.l - o.l;
            return this.dif - o.dif;
            
        }
    }
}