import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumToZero {
    public static List<List<Integer>> searchTriplets(int[] arr) {
        // result
        List<List<Integer>> result = new ArrayList<>();
        // basic check
        if(arr.length < 3) return result;
        // sort nums array
        Arrays.sort(arr);
        // use two points
        for(int i = 0; i < arr.length - 2; i++){
            int target = -arr[i];
            int start = i + 1;
            int end = arr.length - 1;
            if(i >= 1 && arr[i] == arr[i - 1]){
                continue;
            }
            while(start < end){
                if(arr[start] + arr[end] < target){
                    start++;
                }else if(arr[start] + arr[end] > target){
                    end--;
                }else{
                    List<Integer> temp = new ArrayList<>();
                    temp.add(arr[i]);
                    temp.add(arr[start]);
                    temp.add(arr[end]);
                    result.add(temp);
                    start++;
                    end--;
                    while(start < end && arr[start] == arr[start - 1]){
                        start++;
                    }
                    while(start < end && arr[end] == arr[end + 1]){
                        end--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(TripletSumToZero.searchTriplets(new int[] { -1,0,1,2,-1,-4 }));
        System.out.println(TripletSumToZero.searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
    }
}
