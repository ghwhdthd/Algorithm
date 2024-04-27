import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,K;
    private static int[] memory;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        memory = new int[K+1];
        memory[0] = 1;
        
        
        for(int i =0; i<N; i++){
            int now = Integer.parseInt(br.readLine());
            for (int j = 1; j < memory.length; j++) {
                if(j-now >= 0){
                    memory[j] = memory[j] + memory[j-now];
                }else{
                    memory[j] = memory[j];
                }

            }
        }
        System.out.println(memory[K]);
    }
}