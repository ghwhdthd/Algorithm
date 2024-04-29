import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, S;
    private static int[] part1;
    private static int[] part2;
    private static List<Integer> li1;
    private static List<Integer> li2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        part1 = new int[N/2];
        part2 = new int[N - part1.length];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < part1.length; i++) {
            part1[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < part2.length; i++) {
            part2[i] = Integer.parseInt(st.nextToken());
        }

        li1 = new ArrayList<>();
        li2 = new ArrayList<>();
        cal(0,part1,0, li1);
        cal(0,part2,0, li2);

        Collections.sort(li1);
        Collections.sort(li2);
//
//        for (int i = 0; i < li1.size(); i++) {
//            System.out.println(li1.get(i));
//        }
//
//        System.out.println();
//
//        for (int i = 0; i < li2.size(); i++) {
//            System.out.println(li2.get(i));
//        }
        int left = li1.size()-1;
        int right = 0;
        long res = 0;
        while (true){
            if(left < 0) break;
            if(right >= li2.size()) break;
            int now = li1.get(left) + li2.get(right);
            if(now == S){
                long lcount = 0;
                while (left >= 0 && li1.get(left) + li2.get(right) == S ){
                    lcount++;
                    left--;
                }
                left++;
                long rcount = 0;
                while (right < li2.size() && li1.get(left) + li2.get(right) == S ){
                    rcount++;
                    right++;
                }
                left--;
                res += lcount * rcount;

            }else if (now > S){
                left--;
            }else{
                right++;
            }
        }
        if(S == 0) res--;
        System.out.println(res);



    }
    private static void cal(int depth, int[] part, int sum, List<Integer> li){
        if(depth >= part.length){
            li.add(sum);
            return;
        }
        //선택 안하고 넘기기
        cal(depth+1, part ,sum, li);
        // 선택하고 넘기기
        sum += part[depth];
        cal(depth+1, part ,sum, li);

    }

}