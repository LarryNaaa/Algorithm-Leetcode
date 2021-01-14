import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_chooseAFlask {
    public static int chooseAFlask(int numOrders, int[] requirements, int flaskTypes, int totalMarks, PairInt[] markings){
        // WRITE YOUR BRILLIANT CODE HERE
        int flaskRange = totalMarks / flaskTypes;
        int minIndex = -1;
        int minLoss = Integer.MAX_VALUE;

        for (int flaskIndex = 0; flaskIndex < flaskTypes; flaskIndex++) {

            int currLoss = 0;
            boolean validFlask = true;

            // Could optimise this further and do binary search between boundaries on array
            List<Integer> markingsPerFlask = new ArrayList<Integer>();
            for (int markIndex = flaskIndex * flaskTypes; markIndex < flaskIndex * flaskTypes + flaskRange; markIndex++)
                markingsPerFlask.add(markings[markIndex].second);

            for (int requirement : requirements) {
                int index = Arrays.binarySearch(markingsPerFlask.toArray(), requirement);

                // If valid index do nothing... Otherwise check for nearest value
                if (index < 0) {
                    // If no valid flask - stop, this one doesn't count
                    if (index * -1 - 1 >= markingsPerFlask.size()) {
                        validFlask = false;
                        break;
                    } else
                        currLoss += markingsPerFlask.get(index * -1 - 1) - requirement;
                }
            }

            if (validFlask) {
                if (minLoss > currLoss) {
                    minLoss = currLoss;
                    minIndex = flaskIndex;
                }
            }
        }

        return minIndex;


    }
    /** Driver class, do not change **/
    static class PairInt {
        int first, second;

        public PairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    public static void main(String[] args){
        int numOrders = 4;
        int[] requirements = {4, 6, 6, 7};
        int flaskTypes = 3;
        int totalMarks = 9;

        PairInt[] markings = new PairInt[] { new PairInt(0, 3), new PairInt(0, 5), new PairInt(0, 7), new PairInt(1, 6),
                new PairInt(1, 8), new PairInt(1, 9), new PairInt(2, 3), new PairInt(2, 5), new PairInt(2, 6), };
        System.out.println(chooseAFlask(numOrders, requirements, flaskTypes, totalMarks, markings));
    }
}
