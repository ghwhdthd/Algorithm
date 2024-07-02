import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static List<Item> itemList;
    private static List<Integer> bagList;

    private static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        itemList = new ArrayList<>();
        bagList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            itemList.add(new Item(w, v));

        }
        for (int i = 0; i < K; i++) {
            int bag = Integer.parseInt(br.readLine());
            bagList.add(bag);
        }
        Collections.sort(bagList);
        Collections.sort(itemList);
        int start = 0;
        int j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < K; i++) {
            int bag = bagList.get(i);
            while (j < itemList.size() && bag >= itemList.get(j).w) {
                pq.add(itemList.get(j++).v);

            }
            if(!pq.isEmpty()){
                int v = pq.poll();
                res += v;
            }
        }
        System.out.println(res);


    }
    private static class Item implements Comparable<Item>{
        int w;
        int v;
        public Item(int w, int v){
            this.w = w;
            this.v = v;
        }

        public int compareTo(Item o){
            if(this.w == o.w) return o.v - this.v;
            return this.w - o.w;
        }
    }
}