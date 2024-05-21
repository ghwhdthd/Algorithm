import java.io.*;
import java.util.*;


public class Main {
    private static int N, x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Point> li = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            li.add(new Point(x, y));
        }
        Collections.sort(li);

        for (int i = 0; i < N; i++) {
            System.out.println(li.get(i).x + " " + li.get(i).y);
        }
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o){
            if(y == o.y) return x - o.x;
            return y - o.y;

        }
    }
}