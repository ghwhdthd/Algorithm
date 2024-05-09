import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (pro(s)) {
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }

    }

    private static boolean pro(String s){
        Deque<Character> stack = new ArrayDeque<>();
        for (int j = 0; j < s.length(); j++) {
            char now = s.charAt(j);
            if(now == '('){
                stack.push(now);
            }else{
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        if(stack.isEmpty()) {
            return true;
        }
        return false;
    }

}