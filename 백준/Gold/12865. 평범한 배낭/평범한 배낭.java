import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int K;
	static int[] weights;
	static int[] values;
	static int[][] dpTable;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		weights = new int[N + 1];
		values = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());

		}
		// 입력 처리 끝

		dpTable = new int[N + 1][K + 1];
		// dpTable[i][w]의 의미 :
		// i 번째 물건까지 고려했고
		// 가방의 남은 용량이 w일 때
		// 가치의 최댓값을 의미
		for (int i = 1; i <= N; i++) { // i는 물건 번호
			for (int w = 1; w <= K; w++) { // w는 임시가방 K는 실제 가방
				// 물건의 무게가 가방보다 더 큰 경우 -> 안들어감
				if (weights[i] > w) {
					dpTable[i][w] = dpTable[i - 1][w];

					// 가방에 들어가는 경우
				} else {
					//						물건 i를 임시가방에 넣었을 때                                , 물건 i를 임시가방에 넣지 않았을 때 중에서 max 값
					dpTable[i][w] = Math.max(values[i] + dpTable[i-1][w - weights[i]], dpTable[i - 1][w]);
				}
			}
		}

		System.out.println(dpTable[N][K]);
	}
}
/*
4 10
5 10
4 40
6 30
3 50

==> 90

4 16
2 40
5 30
10 50
5 10

==> 90

5 675
331 4015
120 8001
265 9209
13 6705
359 809

==> 23915

*/
