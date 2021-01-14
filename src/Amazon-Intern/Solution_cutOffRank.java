import java.util.Arrays;

public class Solution_cutOffRank {
    public static int countLevelUpPlayers(int cutOffRank, int num, int[] scores) {
        if(cutOffRank == 0) return 0;

        Arrays.sort(scores);

        int currRank = 1;
        for(int i = scores.length - 1; i >= 0; i--){
            int count = 1;
            while(i - 1 >= 0 && scores[i] == scores[i-1]){
                count++;
                i--;
            }
            currRank += count;
            if(currRank > cutOffRank) return num - i;
        }

        return num;
    }
    public static void main(String[] args){

        int cutOffRank = 4;
        int num = 5;
        int[] scores = {2, 2, 3, 4, 5};

        System.out.println(countLevelUpPlayers(cutOffRank, num, scores));
    }
}
