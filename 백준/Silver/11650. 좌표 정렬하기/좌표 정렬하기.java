import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Node> li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            li.add(new Node(x, y));
        }
        Collections.sort(li);
        for (int i = 0; i < N; i++) {
            System.out.println(li.get(i).x + " " +li.get(i).y);
        }

    }
    private static class Node implements Comparable<Node>{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node o){
            if(x == o.x) return y - o.y;
            return x - o.x;
        }
    }

}