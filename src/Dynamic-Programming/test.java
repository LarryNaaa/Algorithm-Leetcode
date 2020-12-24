import java.util.Arrays;
import java.util.Comparator;

public class test {
    public static int findMinArrowShots(int[][] points) {
        if(points.length < 1) return 0;
        // increasing sort points by the end
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                return arr1[1] - arr2[1];
            }
        });
        // initial end and count
        int end = points[0][1], count = 1;
        //traverse each points
        for(int[] point : points){
            // non overlapping, get new end, count + 1
            if(point[0] > end){
                end = point[1];
                count++;
            }
        }
        // add the last arrow
        return count;
    }

    public static void main(String[] args) {
        int[][] input = {{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(findMinArrowShots(input));;
    }
}
