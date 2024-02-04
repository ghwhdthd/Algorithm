import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static long[][] dp; // dp[남은 큰 알약의 개수 : W ][남은 작은 알약의 개수 : H]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //30개의 알약이 들어온다고 가정했을때 -> 30개의 큰 알역을 먹으면 30개의 작은 알약이 생기니까 30개가 각각 최대임
        dp = new long[31][31];
        //input
        while (true){
            int W = Integer.parseInt(br.readLine());
            if(W == 0) break;
            //처음에는 큰 알약 개수만 주어진 상태니까
            long res = eatPills(W,0);
            System.out.println(res);
        }
//        for(int i=0; i<31; i++){
//            for(int j=0; j<31; j++){
//                if(dp[i][j] == 0) continue;
//                System.out.println("큰 알약 : " + i + " 작은 알약 : "+ j +" -> " +dp[i][j]);
//            }
//        }

    }
    private static long eatPills(int W, int H){
        //만약 현재 조건이 이미 저잗되어 있었다면 return
        if(dp[W][H] != 0) return dp[W][H];
        //만약 큰 알약을 다 먹은 경우는 무조건 작은 알약을 먹는 경우 밖에 안남았으니까 1가지
        if(W == 0) return 1;

        //큰 알약을 먹은 경우 -> 작은 알약이 생김
        dp[W][H] = eatPills(W-1,H+1);
        //작은 알약이 남아있을 때 작은 알약을 먹은 경우 추가-> 작은 알약만 없어짐
        if(H>0) dp[W][H] += eatPills(W, H-1);

        return dp[W][H];

    }
}