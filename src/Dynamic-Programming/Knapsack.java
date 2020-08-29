public class Knapsack {
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // 1. brute-force recursion
        // return knapsackRecursive(profits, weights, capacity, 0);

        // 2. top-down dynamic programming with memoization: optimize coding by solving overlapping sub-problems
        // we can use a two-dimensional array to store the result of all the solved sub-problems
        //Integer[][] dp = new Integer[profits.length][capacity+1];
        //return knapsackMemoizationDP(dp, profits, weights, capacity, 0);

        // 3. bottom-up dynamic programming
        return knapsackButtonUpDP(profits, weights, capacity);
    }

    // a brute-force recursive solution:
    // the time complexity of this algorithm is exponential O(2 of n power)
    // the space complexity is O(capacity)
    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        // base check, if the capacity is not more than zero, which means we don't have any capacity;
        // or if the current index is not less than the length of the profits array,
        // which means we've recursively gone through all the elements.
        // In both cases, we'll stop the recursion, and return zero.
        if(capacity <= 0 || currentIndex >= profits.length) return 0;

        // first case
        int profit1 = 0;
        // if the weight of the current element is less than or equal to the capacity,
        // so we can have recursive call after choosing current element.
        if(weights[currentIndex] <= capacity){
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);
        }

        // second case
        // if the weight of current element exceeds the capacity, we skip the current element
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        // get the maximum value of these two cases
        return Math.max(profit1, profit2);
    }

    // top-down dynamic programming with memoization:
    // the time complexity of this algorithm is O(profits.length x capacity)
    // the space complexity is O(profits.length x capacity)
    private int knapsackMemoizationDP(Integer[][] dp,  int[] profits, int[] weights, int capacity, int currentIndex) {
        // base check, if the capacity is not more than zero, which means we don't have any capacity;
        // or if the current index is not less than the length of the profits array,
        // which means we've recursively gone through all the elements.
        // In both cases, we'll stop the recursion, and return zero.
        if(capacity <= 0 || currentIndex >= profits.length) return 0;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][capacity] != null) return dp[currentIndex][capacity];

        // first case
        int profit1 = 0;
        // if the weight of the current element is less than or equal to the capacity,
        // so we can have recursive call after choosing current element.
        if(weights[currentIndex] <= capacity){
            profit1 = profits[currentIndex] + knapsackMemoizationDP(dp, profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);
        }

        // second case
        // if the weight of current element exceeds the capacity, we skip the current element
        int profit2 = knapsackMemoizationDP(dp, profits, weights, capacity, currentIndex + 1);

        // memorize the maximum value of these two cases
        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

    // bottom-up dynamic programming:
    // the time complexity of this algorithm is O(profits.length x capacity)
    // the space complexity is O(profits.length x capacity)
    private int knapsackButtonUpDP(int[] profits, int[] weights, int capacity){
        // basic check
        if(profits.length <= 0 || capacity <= 0 || profits.length != weights.length)
            return 0;

        // use a two-dimensional array to store the result of all solved sub-problems
        int[][] dp = new int[profits.length][capacity+1];

        // populate the capacity = 0 columns, if we have 0 capacity, we have 0 profit
        for(int i = 0; i < profits.length; i++){
            dp[i][0] = 0;
        }

        // populate the profits' index = 0 rows, we will take it if it is not more than the capacity
        for(int i = 0; i < capacity + 1; i++){
            if(weights[0] <= i){
                dp[0][i] = profits[0];
            }
        }

        // process all sub-arrays for all the capacities
        for(int i = 1; i< profits.length; i++){
            for(int j = 1; j <= capacity; j++){
                int profit1 = 0, profit2 = 0;
                // include the item
                if(weights[i] <= j){
                    profit1 = profits[i] + dp[i-1][j - weights[i]];
                }
                // exclude the item
                profit2 = dp[i-1][j];
                // take maximum value
                dp[i][j] = Math.max(profit1, profit2);
            }
        }
        // maximum profit will be at the bottom-right corner
        return dp[profits.length - 1][capacity];
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
