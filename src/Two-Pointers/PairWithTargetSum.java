public class PairWithTargetSum {
    public static int[] search(int[] arr, int targetSum) {
        // TODO: Write your code here
        int[] result = new int[] { -1, -1 };
        int start = 0, end = arr.length - 1;
        while(start < end){
            if(arr[start] + arr[end] > targetSum){
                end--;
            }else if(arr[start] + arr[end] < targetSum){
                start++;
            }else {
                result[0] = start;
                result[1] = end;
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }
}
