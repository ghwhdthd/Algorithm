import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int MAX = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Line> li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            li.add(new Line(start, 0));
            li.add(new Line(end, 1));
        }
        Collections.sort(li);
        int res = 0;
        for(Line now : li){
            if(now.type == 0){
                res++;
                MAX = Math.max(MAX, res);
            }else{
                res--;
            }
        }
        System.out.println(MAX);
    }
    private static class Line implements Comparable<Line>{
        int value;
        int type; // start 는 0, end 는 1
        public Line(int value, int type){
            this.value = value;
            this.type = type;
        }
        public int compareTo(Line o){
            if(value == o.value) return o.type - type;
            return value - o.value;
        }
    }
}