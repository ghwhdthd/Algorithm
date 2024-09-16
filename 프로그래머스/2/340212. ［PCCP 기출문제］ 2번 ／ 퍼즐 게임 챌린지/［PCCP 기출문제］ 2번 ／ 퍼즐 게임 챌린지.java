import java.util.*;

class Solution {
    private static long limit;
    private static int[] diffs;
    private static int[] times;
    // 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값 구하기
    // 이분 탐색으로 풀면 될 거 같음
    // level의 max값은 몇으로 설정해? diff의 max값으로 설정하면 됨
    
    private static boolean cal(int level){
        long time_cur = 0;
        long time = 0; 
        
        for(int i = 0; i < diffs.length; i++){
            int diff = diffs[i];
            time_cur = times[i];
            
            if(diff <= level){
                time += time_cur;
            }else{
                if(i == 0){
                    time += (diff - level) * (time_cur) +time_cur;    
                }else{
                    int time_prev = times[i-1];
                    time += (diff - level) * (time_prev + time_cur) +time_cur;    
                }
                
            }
            
            //System.out.println(time);
            if(time > limit) return false;
            
        }   
        //System.out.println(time);
        return true;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        int l = 1;  // 최솟값
        int r = Arrays.stream(diffs).max().getAsInt();  // diff 배열에서 최대값을 찾음

        //cal(2);
        // 이진 탐색 실행
        while (l < r) {
            int mid = (l + r) / 2;

            if (cal(mid)) {
                // 주어진 level(mid)에서 퍼즐을 해결할 수 있다면 더 낮은 범위에서 탐색
                r = mid;
            } else {
                // 주어진 level(mid)에서 퍼즐을 해결할 수 없다면 더 높은 범위에서 탐색
                l = mid + 1;
            }
        }

        return l;  // 최종적으로 가능한 최소 숙련도 반환
    }
    
}