import java.util.Arrays;

public class SubsetSum {


    public boolean canPartition(int[] num, int sum){
        return optimalBottomUoDP(num, sum);
    }

    // bottom-up DP with a two-dimensional array
    public boolean bottomUpDp(int[] num, int sum){
        // a two-dimensional array to store the results
        // one dimension is the index of the num array, which means the different subset;
        // another dimension is the different sum
        boolean[][] dp = new boolean[num.length][sum + 1];

        // populate the sum=0 column, we can get sum = 0 from a empty set
        for(int i = 0; i < num.length; i++){
            dp[i][0] = true;
        }
        // populate the index=0 row, if the sum doesn't exceed the value of the subset
        for(int j = 0; j <= sum; j++){
            dp[0][j] = num[0] >= j;
        }
        // populate the results of all other sub-problem
        for(int i = 1; i < num.length; i++){
            for(int j = 1; j <= sum; j++){
                // first case: exclude the current number
                // we can get the sum S without the number at index i
                if(dp[i-1][j]){
                    dp[i][j] = dp[i-1][j];
                }else if(num[i] <= j){
                    // second case: include the current number
                    // we can see if we can find the subset to get the remaining sum
                    // the remaining sum means, the current sum - the current number
                    dp[i][j] = dp[i-1][j - num[i]];
                }
            }
        }
        // the result is at the bottom right corner
        return dp[num.length - 1][sum];
    }

    public static boolean optimalBottomUoDP(int[] num, int sum){
        // the current results just need the previous results,
        // and out final result is at the bottom right corner,
        // so we don't need the whole two-dimensional array
        boolean[] dp = new boolean[sum+1];

        // if the sum = 0, we can have a 0 sum with an empty set
        dp[0] = true;

        // we can have a subset only when the sum is equal to its value
        for(int i = 1; i <= sum; i++){
            dp[i] = num[0] == i;
        }

        // process all subsets for all sums
        for(int i = 1; i < num.length; i++){
            // update dp array from back to front,
            // so that the previous results don't affect the later results
            for(int j = sum; j >= 0; j--){
                // if the dp[j] == true, it means that we can get the subset without
                // num[i], so we can move on to the next number;
                // else we need to see if we can find the subset to get the remaining
                // sum.
                if(!dp[j] && j >= num[i]){
                    dp[j] = dp[j - num[i]];
                }
            }
        }
        // get the final result at the right of the dp array
        return dp[sum];
    }

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = { 1, 2, 3, 7 };
        System.out.println(ss.canPartition(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.canPartition(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(ss.canPartition(num, 6));
    }
}
