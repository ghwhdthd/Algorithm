import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static String[] li;
    private static Set<Character> set;
    private static boolean[] isLearn;
    private static List<Character> candi;
    private static int MAX = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        li = new String[N];
        set = new HashSet<>();
        isLearn = new boolean[26];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            String s1 = s.substring(4, s.length()-4);
            li[i] = s1;
            for (int j = 0; j < li[i].length(); j++) {
                set.add(li[i].charAt(j));
            }
        }



        //a n t i c 은 무조건 배워야함
        if (K < 5) {
            System.out.println(0);
            return;
        }
        String mustLearn = "antic";
        for (int i = 0; i < mustLearn.length(); i++) {
            isLearn[mustLearn.charAt(i) - 'a'] = true;
        }
        candi = new ArrayList<>();

        //이미 배운 단어는 제외하고 나머지를 candi에 넣기
        for(char now : set){
            if(isLearn[now - 'a']) continue;
            candi.add(now);
        }
        //배워야할 단어 리스트 저장해놓고
        // 하나씩 배워 -> 그 다음에 체크해
        // - 체크하는 방법이 뭘까? - 완탐으로 하자
        // 배우고 체크 반복하면 끝
        if(K >= candi.size() + 5){
            System.out.println(N);
            return;
        }

        dfs(0, 5);

        System.out.println(MAX);
    }
    private static void dfs(int start, int leanCount){
        if(leanCount == K){
            int tmp = check();
            MAX = Math.max(MAX, tmp);
            return;
        }
        for (int i = start; i < candi.size(); i++) {
            int idx = candi.get(i) - 'a';
            // 현재 단어 배우거나
            if(!isLearn[idx]){
                isLearn[idx] = true;
                dfs(i + 1, leanCount + 1);
                isLearn[idx] = false;
            }
        }
    }
    private static int check(){
        int cnt = 0;

        for (int i = 0; i < li.length; i++) {
            boolean isCounting = true;
            for (int j = 0; j < li[i].length(); j++) {
                if(!isLearn[li[i].charAt(j) - 'a']) {
                    isCounting = false;
                    break;
                }
            }
            if(isCounting) cnt++;
        }

        return cnt;
    }
}