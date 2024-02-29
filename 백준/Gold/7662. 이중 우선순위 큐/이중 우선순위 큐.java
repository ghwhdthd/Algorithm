import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static int T,K;
    private static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            pro();
        }
    }
    private static void pro()throws IOException{
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            String func = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if(func.equals("I")){
                int cnt=0;
                // 숫자 num의 value가 null이 아닌 경우에만 조회
                if(map.get(num) != null) cnt = map.get(num);
                map.put(num, cnt+1);

            }else{
                if(map.size()==0) continue;
                if(num == 1){
                    //최대값 키
                    int maxkey = map.lastKey();
                    int cnt = map.get(maxkey);

                    if(cnt == 1){
                        map.remove(maxkey);
                    }else{
                        map.put(maxkey,cnt -1);
                    }
                }else{
                    //최소값 키
                    int minkey=map.firstKey();
                    int cnt = map.get(minkey);
                    if(cnt == 1){
                        map.remove(minkey);
                    }else{
                        map.put(minkey,cnt -1);
                    }
                }

            }
        }
        if(map.size() == 0){
            System.out.println("EMPTY");
        }else{
            System.out.println(map.lastKey() + " " + map.firstKey());
        }

    }
}