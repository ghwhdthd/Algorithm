import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Map<String, Double> map;
    private static List<String> li;
    private static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        map = new HashMap<>();
        li = new ArrayList<>();
        while (true){
            String now = br.readLine();
            if(now == null || now.length() == 0){
                break;
            }
            if(map.get(now) == null){
                map.put(now, 1.0);
                li.add(now);
            }else{
                double value = map.get(now);
                map.put(now, value+1);
            }
            total++;
        }

        Collections.sort(li);
        for (int i = 0; i < li.size(); i++) {
            double nameCount = map.get(li.get(i));
            double percent = nameCount / total * 100;
            sb.append(li.get(i) + " " + String.format("%.4f", percent)).append('\n');
        }
        System.out.println(sb);
    }
}