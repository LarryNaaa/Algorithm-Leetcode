import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_multiprocessing {
    public static int multiprocessorSystem(int[] ability, int num,  int processes){
        // WRITE YOUR BRILLIANT CODE HERE
        if(ability.length < 1 || num < 1 || processes < 1) return 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int a : ability){
            maxHeap.add(a);
        }

        int res = 0;
        while(processes > 0){
            int temp = maxHeap.remove();
            processes -=  temp;
            res++;
            int newAbility = temp / 2;
            maxHeap.add(newAbility);
        }

        return res;
    }
    public static void main(String[] args){

//        int[] ability  = {3, 1, 7, 2, 4};
//        int num = 5;
//        int processes = 15;

//        int[] ability  = {2, 1, 5, 3, 1};
//        int num = 5;
//        int processes = 17;

        int[] ability  = {3, 1, 4, 2};
        int num = 4;
        int processes = 3;
        System.out.println(multiprocessorSystem(ability, num, processes));
    }
}
