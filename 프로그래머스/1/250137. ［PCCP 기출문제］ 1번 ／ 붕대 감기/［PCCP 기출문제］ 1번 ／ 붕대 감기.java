class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        
    
        for(int i =0; i<attacks.length; i++){
            if(i==0){
                answer -= attacks[i][1];
                if(answer <=0) return -1;
                continue;
            }
            int before = attacks[i-1][0];
            int now = attacks[i][0];
            int damage = attacks[i][1];
            
            int healtime =now - before -1;
            int heal = healtime * bandage[1];
            
            
            if(healtime >= bandage[0]){
                int tmp = healtime / bandage[0];
                heal+=(bandage[2]*tmp);
            }
            
            answer = heal + answer;
            
            if(answer > health) answer = health;
            
            answer -= damage;
            if(answer <=0) {
                return -1;
            }
        }
        
        return answer;
    }
}