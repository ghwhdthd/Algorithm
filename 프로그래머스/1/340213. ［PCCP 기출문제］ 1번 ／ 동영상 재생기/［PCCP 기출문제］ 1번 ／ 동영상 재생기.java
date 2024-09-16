import java.util.*;

class Solution {
    private int timeToSecond(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        int sec = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        
        return sec;
    }
    private String secondToTime(int time){
        int min = time / 60;
        int sec = time % 60;
        
        return String.format("%02d:%02d", min, sec);
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int v = timeToSecond(video_len);
        int p = timeToSecond(pos);
        int os = timeToSecond(op_start);
        int oe = timeToSecond(op_end);
        
        for(String command : commands){
            if(os <= p && p <= oe){
                p = oe;
            }
            
            if(command.equals("prev")){
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
            
            if(os <= p && p <= oe){
                p = oe;
            }
        }
        
        return secondToTime(p);
    }
}