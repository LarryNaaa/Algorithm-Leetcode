import java.util.Arrays;
import java.util.Scanner;

public class Solution_minContainerSize {
    public static int minContainerSize(int d, int[] P) {
        int length = P.length;
        if(d > length) return -1;
        int dp[][] = new int[length+1][d+1];

        for(int i = 0; i <= length; i++){
            for(int j = 0; j <= d; j++){
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        dp[0][0] = 0;

        for(int i = 1; i <= length; i++){
            for(int k = 1; k <= d; k++){
                int max_d = 0;
                for(int j = i - 1; j >= k - 1; j--){
                    max_d = Math.max(max_d, P[j]);
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + max_d);
                }
            }
        }

        return dp[length][d];
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int d = 5;
        int[] P = {10,2,20,5,15,10,1};
        scanner.close();
        System.out.println(minContainerSize(d, P));
    }
}
