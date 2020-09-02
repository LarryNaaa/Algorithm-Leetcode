public class MinSizeSubArraySum {
    public static int findMinSubArray(int S, int[] arr) {
        // TODO: Write your code here
        // basic check
        if(arr.length == 0) return 0;
        int minLength = Integer.MAX_VALUE, sum = 0, length = 0;
        int start = 0;
        for(int end = 0; end < arr.length; end++){
            // get the sum of window
            sum += arr[end];
            // if the sum is not less than number s,
            // which means that we've a subarray that satisfied the conditions
            while(sum >= S){
                // get current window length
                length = end - start + 1;
                // get the minimum length
                minLength = Math.min(minLength, length);
                // shrink window
                sum -= arr[start];
                start++;
            }
        }
        // if the minLength == 0, which means that we don't find a subarray, so return 0;
        // else return the minLength
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
