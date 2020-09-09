import java.util.Arrays;

public class TripletSumCloseToTarget {
    public int threeSumClosest(int[] nums, int target) {
        // result
        int result = 0;
        int minSum = Integer.MAX_VALUE;
        // sort nums
        Arrays.sort(nums);
        // use a loop to convert three pointers problem to two pointers
        for(int i = 0; i < nums.length; i++){
            int tempTarget = target - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            // we use two Pointers, one to the beginning and one to the end,
            // and then walk through the middle until the condition is met or the two Pointers meet
            while(start < end){
                if(nums[start] + nums[end] == tempTarget){
                    return target;
                }
                int sum = Math.abs(tempTarget - (nums[start] + nums[end]));
                if(sum < minSum){
                    minSum = sum;
                    result = nums[i] + nums[start] + nums[end];
                }
                if(nums[start] + nums[end] < tempTarget){
                    start++;
                }else if(nums[start] + nums[end] > tempTarget){
                    end--;
                }
            }
        }
        return result;
    }
}
