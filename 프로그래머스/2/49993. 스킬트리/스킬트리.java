import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        boolean check = true;
        for(int i = 0; i < skill_trees.length; i++){
            String now = skill_trees[i];
            String need = getNeedSkill(skill, now);
            check = true;
            for(int j = 0; j < need.length(); j++){
                if(skill.charAt(j) != need.charAt(j)) check = false;
            }
            if(check) answer++;
        }
        
        return answer;
    }
    private static String getNeedSkill(String skill, String skill_tree){
        String need = "";
        for(int i = 0; i < skill_tree.length(); i++){
            for(int j = 0; j < skill.length(); j++){
                if(skill.charAt(j) == skill_tree.charAt(i)) need += skill.charAt(j) + "";
            }
        }
        
        return need;
    }
}