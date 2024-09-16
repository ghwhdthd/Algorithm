        // #10초 전으로 이동 
        //     # - 현재 위치가 10초 미만인 경우 처음으로 이동
        // //10초 후로 이동
        //     # 남은 시간이 10초 미만인 경우 마지막으로 이동
        // // 오프닝 건너뛰기
            
        // 한번 움직일 때마다  pos가 오프닝 구간인지 확인, 비디오 처음인지 확인, 비디오 끝인지 확인
import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        StringTokenizer st = new StringTokenizer(video_len, ":");
        int v = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(pos, ":");
        int p = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(op_start, ":");
        int os = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(op_end, ":");
        int oe = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        
        for(String command : commands){
            // 현재 pos의 위치가 오프닝이면 건너뛰기
            if(p >= os && p <= oe){
                p = oe;
            }
            
            if(command.equals("prev") ){
                p -= 10;
                if(p < 0){
                    p = 0;
                }
            }else{
                p += 10;
                if(p > v){
                    p = v;
                }
            }
            
            // 현재 pos의 위치가 오프닝이면 건너뛰기
            if(p >= os && p <= oe){
                p = oe;
            }
        }
        
        int min = p / 60;
        int sec = p % 60;
        String answer;
        
        if(min < 10){
            answer = "0" + min + ":";
        }else{
            answer = min + ":";
        }
        
        if(sec < 10){
            answer += "0" + sec;
        }else{
            answer += sec + "";
        }
        return answer;
        
    }
        
        
        
}