package DP;

public class Fibonacci {
    // time:O(2^n)
    public static int fib(int n){
        if(n == 1 || n == 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    // time:O(n) space:O(n)
    public static int memoization(int n){
        int[] memo = new int[n + 1];
        return memoizationFib(memo, n);
    }

    public static int memoizationFib(int[] memo, int n){
        if(n == 1 || n == 2) return 1;
        if(memo[n] != 0) return memo[n];
        memo[n] = memoizationFib(memo, n - 2) + memoizationFib(memo, n - 1);
        return memo[n];
    }

    // time:O(n) space:O(n)
    public static int tabulation(int n){
        if(n < 0) return 0;
        if(n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    // time:O(n) space:O(1)
    public static int tabulationWithSmallSpace(int n){
        if(n < 0) return 0;
        if(n == 1 || n == 2) return 1;
        int prev = 1, curr = 1;
        for(int i = 3; i <= n; i++){
            int temp = curr;
            curr = prev + temp;
            prev = temp;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(fib(n));
        System.out.println(memoization(n));
        System.out.println(tabulation(n));
        System.out.println(tabulationWithSmallSpace(n));
    }
}
