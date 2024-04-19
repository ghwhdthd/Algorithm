import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, drop_node;
    static int[] input;
    static int root;
    static boolean[] visit;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        StringTokenizer st= new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(st.nextToken());
            if(input[i] == -1) root = i;
        }
        drop_node = Integer.parseInt(br.readLine());


        drop(drop_node);
        visit = new boolean[N];


        count(root);

        System.out.println(cnt);

    }

    private static void count(int node) {
        visit[node] = true;
        boolean isLeaf = true;
        if(input[node] != -2){
            for(int i=0; i<N; i++){
                //아직 지나지 않은 자식노드를 탐색
                if(input[i] == node && visit[i] == false){
                    count(i);
                    //재귀가 다 끝나면 여태까지 지나왔던 leaf 가 아닌 노드는
                    // isleaf를 false 로 바꿔줘서 상태변경
                    isLeaf = false;

                }
            }
            if(isLeaf) cnt++;
        }



    }

    private static void drop(int drop_node) {
        input[drop_node] = -2;
        //지워야할 node 를 -2로 바꿈
        for(int i=0; i<N; i++){
            if(input[i] == drop_node) {
                drop(i);
                //지운 node를 부모로 갖고 있는 애 찾아서 지움
            }

        }
    }

}
