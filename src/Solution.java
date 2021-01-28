import java.io.IOException;
import java.util.*;

public class Solution {
    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> perm = new ArrayList<>();

    public static List<List<Integer>> permutation(int[] nums) {
        boolean[] visited = new boolean[nums.length];

        dfs(nums, visited);

        return ans;
    }

    public static void dfs(int[] nums, boolean[] visited){
        if(perm.size() == nums.length){
            ans.add(new ArrayList(perm));
            return;
        }

        for(int i = 0; i < nums.length; ++i){
            if(visited[i]) continue;

            visited[i] = true;
            perm.add(nums[i]);

            dfs(nums, visited);

            perm.remove(perm.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(permutation(new int[] {1, 2, 3}).toString());
    }
}
