import java.util.*;
class Solution {
    private static int[] indegree;
    private static List<List<Integer>> li;
    private static int answer;
    public int solution(String skill, String[] skill_trees) {
        answer = 0;
        indegree = new int[26];
        li = new ArrayList();
        for(int i = 0; i < 26; i++){
            li.add(new ArrayList());
        }
        
        //위상정렬을 위한 리스트 채우기
        for(int i = 0; i < skill.length(); i++){
            int bSkill = skill.charAt(i) - 'A';
            for(int j = i+1; j<skill.length(); j++){
                int aSkill = skill.charAt(j) - 'A';
                li.get(bSkill).add(aSkill);
            }
        }
        
        for(int i = 0; i < skill_trees.length; i++){
            String needSkill = getNeedSkill(skill_trees[i], skill);
            initIndegree(skill);
            topologySort(needSkill);
        }
        
        return answer;
    }
    
    
    private static void initIndegree(String skill){
        for(int i = 0; i < skill.length(); i++){
            indegree[skill.charAt(i) - 'A'] = i;
        }
    }
    
    
    private static String getNeedSkill(String skill_tree, String skill){
        String needSkill = "";
        for(int i = 0; i < skill_tree.length(); i++){
            for(int j = 0; j < skill.length(); j++){
                if(skill_tree.charAt(i) == skill.charAt(j)) needSkill += skill.charAt(j) + "";
            }
        }
        return needSkill;
        
    }
    
    
    private static void topologySort(String needSkill){
        
        Deque<Integer> que = new ArrayDeque();
        if(needSkill.length() == 0) {
            answer++; 
            return;
        }
        int first = needSkill.charAt(0) - 'A';
        if(indegree[first] > 0) return;
        que.add(first);
        
        for(int i = 0; i < needSkill.length(); i++){
            if(que.size() == 0) return;
            int now = que.poll();
            if((char)(now + 'A') != needSkill.charAt(i)) return;
            for(int next : li.get(now)){
                indegree[next]--;
                if(indegree[next] == 0) que.add(next);
            }
            
        }
        // System.out.println(needSkill);
        answer++;
    }
}