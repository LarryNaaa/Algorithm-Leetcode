package DP;

public class Partition {
    public static boolean canPartition(int[] nums) {
        // sum/2
        int sum = 0, target = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        // if the sum is odd num, we cannot get two subsets
        if(sum % 2 != 0){
            return false;
        }else{
            target = sum / 2;
        }

        // dp[i][j] is whether the sum of first 'i' elements is equal to the target num 'j' or not
        boolean[][] dp = new boolean[nums.length+1][target+1];

        // initial dp[][]
        for(int j = 0; j <= target; j++){
            // when no any elements to choose
            dp[0][j] = false;
        }
        for(int i = 0; i <= nums.length; i++){
            // when target num is 0
            dp[i][0] = true;
        }

        //travers each element
        for(int i = 1; i <= nums.length; i++){
            // traverse each target sum
            for(int j = 1; j <= target; j++){
                // if the val of current element > target num, can't choose this element
                if(j < nums[i-1]){
                    dp[i][j] = dp[i - 1][j];
                }else{
                    // otherwise, two choices: choose or not choose
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i-1]];
                }
            }
        }

         for(int i = 0; i <= nums.length; i++){
             for(int j = 0; j <= target; j++){
                 System.out.print(dp[i][j] + "    ");
             }
             System.out.println();
         }
        System.out.println("----------");

        return dp[nums.length][target];

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5};
        System.out.println(canPartition(nums));;
    }
}
