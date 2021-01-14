import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_earliestTime {
    public static int earliestTime(int numOfBuildings, int[] buildingOpenTime, int[] offloadTime) {
        // WRITE YOUR BRILLIANT CODE HERE
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
//
//        for(int time : offloadTime){
//            maxHeap.add(time);
//        }
//
//        for(int time : buildingOpenTime){
//            minHeap.add(time);
//        }
//
//        int res = 0;
//        while(!maxHeap.isEmpty() && !minHeap.isEmpty()){
//            res = Math.max(res, maxHeap.remove() + minHeap.remove());
//            maxHeap.remove();
//            maxHeap.remove();
//            maxHeap.remove();
//        }
//
//        return res;

        Arrays.sort(buildingOpenTime);
        Arrays.sort(offloadTime);

        int latestTime = Integer.MIN_VALUE;
        for (int x = 0; x < numOfBuildings; x++)
            latestTime = Math.max(latestTime, buildingOpenTime[x] + offloadTime[numOfBuildings * 4 - x * 4 - 1]);

        return latestTime;
    }


    public static void main(String[] args){
        int numOfBuildings = 2;
        int[] buildingOpenTime = {8, 10};
        int[] offloadTime = {2, 2, 3, 1, 8, 7, 4, 5};
        System.out.println(earliestTime(numOfBuildings, buildingOpenTime, offloadTime));
    }
}
