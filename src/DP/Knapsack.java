package DP;

public class Knapsack {
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // state: item, capacity
        // dp[i][j]: i is
        int[][] dp = new int[profits.length + 1][capacity + 1];

        // no value when item = 0 or capacity = 0
        for(int i = 0; i < profits.length; i++){
            dp[i][0] = 0;
        }
        for(int j = 0; j < capacity + 1; j++){
            dp[0][j] = 0;
        }

        // traverse each item and capacity
        for(int i = 1; i <= profits.length; i++){
            for(int j = 1; j <= capacity; j++){
                // current capacity < item's weight, cannot put it into
                if(j < weights[i - 1]){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    // otherwise, get the max value of two actions:
                    // 1. do not put item 'i' in
                    // 2. put item 'i' in
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + profits[i - 1]);
                }
            }
        }

        for(int i = 0; i <= profits.length; i++){
            for(int j = 0; j <= capacity; j++){
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }

        return dp[profits.length][capacity];
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {4, 2, 3};
        int[] weights = {2, 1, 3};
        int maxProfit = ks.solveKnapsack(profits, weights, 4);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 1);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
