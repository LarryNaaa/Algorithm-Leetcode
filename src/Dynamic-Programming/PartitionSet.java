import java.lang.reflect.Array;
import java.util.Arrays;

public class PartitionSet {

    static boolean canPartition(int[] num) {
        // get the sum of all the elements in array
        int sum = 0;
        for(int elem : num){
            sum += elem;
        }
        // if the sum is add, return false
        if (sum % 2 != 0)
            return false;

        // get the sum of subset
        int target = sum / 2;

        // 1. top-down memoization DP
        // a two-dimensional array to store result of all sub-problems
//        Boolean[][] dp = new Boolean[num.length][target + 1];
//        return memoizationDP(num, target, 0, dp);

        // 2. bottom-up DP
        return bottomUpDP(num, target, 0);
    }

    private static boolean memoizationDP(int[] num, int target, int index, Boolean[][] dp){
        // basic check
        if(target == 0 && index < num.length){
            return true;
        }
        if(target < 0 || index >= num.length){
            return false;
        }

        // if we've the similar problem, return the result
        if(dp[index][target] != null)
            return dp[index][target];

        // first case, recursive call include this item
        if(num[index] <= target){
            if (memoizationDP(num, target - num[index], index + 1, dp)){
                dp[index][target] =
                        memoizationDP(num, target - num[index], index + 1, dp);
                return true;
            }
        }
        // second case, recursive call exclude this item
        dp[index][target] = memoizationDP(num, target, index + 1, dp);
        return dp[index][target];
    }

    private static boolean bottomUpDP(int[] num, int target, int index){
        // a two-dimensional array
        boolean[][] dp = new boolean[num.length][target + 1];
        // if the target columns = 0, return false
        for(int i = 0; i < num.length; i++){
            dp[i][0] = true;
        }
        // if the index rows = 0
        for(int i = 0; i <= target; i++){
            dp[0][i] = num[0] == i;
        }
        // get the results of other sub-array
        for(int i = 1; i < num.length; i++){
            for(int j = 1; j <= target; j++){
                //
                if(dp[i-1][j]){
                    dp[i][j] = dp[i - 1][j];
                }else if(j >= num[i]){
                    dp[i][j] = dp[i - 1][j - num[i]];
                }
            }
        }
        return dp[num.length-1][target];
    }

    public static void main(String[] args) {
        PartitionSet ps = new PartitionSet();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
