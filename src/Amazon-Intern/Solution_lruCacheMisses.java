import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution_lruCacheMisses {
    public static int lruCacheMisses(int num, int[] pages, int maxCacheSize) {
        // WRITE YOUR BRILLIANT CODE HERE
        if(pages.length < 1 || maxCacheSize == 0) return 0;
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        int miss = 0;
        for(int page : pages){
            if(!set.contains(page)){
                miss++;
                set.add(page);
                q.add(page);
                if(q.size() > maxCacheSize) set.remove(q.remove());
            }else{
                q.remove();
                q.add(page);
            }
        }
        return miss;
    }
    public static void main(String[] args){
        int num = 4;
        int[] pages = {26, 16, 56, 42, 90, 24, 9, 38, 83, 41};
        int maxCacheSize = 2;

        System.out.println(lruCacheMisses(num, pages, maxCacheSize));
    }
}
