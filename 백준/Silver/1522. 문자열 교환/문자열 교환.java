import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char[] arr;
    private static int aCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        arr= new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i]= s.charAt(i);
            if(arr[i] == 'a') aCount++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <arr.length ; i++) {
            //acount 만큼 봐야함
            int bCount = 0;
            for (int j = i; j < i+aCount; j++) {
                if(arr[j%arr.length] == 'b') bCount++;
            }
            res = Math.min(res,bCount);
        }
        System.out.println(res);

    }
}