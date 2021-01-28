# DFS

## Backtracking

### Combination

#### 输入数组无重复，每个元素只能被选择一次

[Leetcode 77. Combinations](https://leetcode.com/problems/combinations/)

```java
    // 输入两个数字 n, k，算法输出 [1..n] 中 k 个数字的所有组合。
    // input: n = 4, k = 2
    // output: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();

    public List<List<Integer>> combination(int n, int k) {
        dfs(n, k, 1);

        return ans;
    }

    public void dfs(int n, int k, int index){
        if(k == 0){
            ans.add(new ArrayList(comb));
            return;
        }

        for(int i = index; i <= n; ++i){
            comb.add(i);

            // i + 1 because each element can only be selected once
            dfs(n, k - 1, i + 1);

            comb.remove(comb.size() - 1);
        }
    }
```

Similar Problem:

[Leetcode 78.Subsets](https://leetcode.com/problems/subsets/)

[Leetcode 216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii/)



#### 输入数组无重复，每个元素可以被选择多次

[Leetcode 39. Combination Sum](https://leetcode.com/problems/combination-sum/)

```java
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        
        return ans;
    }
    
    public void dfs(int[] candidates, int target, int index){
        if(target == 0){
            ans.add(new ArrayList(comb));
            return;
        }
        
        for(int i = index; i < candidates.length; ++i){
            if(candidates[i] > target) continue;
            
            comb.add(candidates[i]);
            
            // keep i because each element can be selected multiple times
            dfs(candidates, target - candidates[i], i);
            
            comb.remove(comb.size() - 1);
        }
    }
```



#### 输入数组有重复，每个元素只能被选择一次

[Leecode 40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

```java
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // sort input array, ready for dfs
        Arrays.sort(candidates);
        
        dfs(candidates, target, 0);

        return ans;
    }
    
    public void dfs(int[] candidates, int target, int index){
        if(target == 0){
            ans.add(new ArrayList(comb));
            return;
        }
        
        for(int i = index; i < candidates.length; ++i){
            if(candidates[i] > target) continue;
            
            // for current level, 
            // if the same element has been selected before, skip current element
            if(i > index && candidates[i] == candidates[i - 1]) continue;
            
            comb.add(candidates[i]);
            
            // i + 1 because each element can only be selected once
            dfs(candidates, target - candidates[i], i + 1);
            
            comb.remove(comb.size() - 1);
        }
    }
```

Similar Problem:

[Leetcode 90. Subsets II](https://leetcode.com/problems/subsets-ii/)



[Leetcode 17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)



### Permutation

#### 输入数组无重复，每个元素只能被选择一次

[Leetcode 46. Permutations](https://leetcode.com/problems/permutations/)

```java
    // 输入一个不包含重复数字的数组 nums，返回这些数字的全部排列。
    // input: [1,2,3]
    // output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> perm = new ArrayList<>();

    public List<List<Integer>> permutation(int[] nums) {
        // use an array to remember elements we have been visited
        boolean[] visited = new boolean[nums.length];

        dfs(nums, visited);

        return ans;
    }

    public void dfs(int[] nums, boolean[] visited){
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
```



#### 输入数组有重复，每个元素只能被选择一次

[Leetcode 47. Permutations II](https://leetcode.com/problems/permutations-ii/)

```java
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> perm = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        
        int[] visited = new int[nums.length];
        
        dfs(nums, 0, visited);
        
        return ans;
    }
    
    public void dfs(int[] nums, int depth, int[] visited){
        if(depth == nums.length){
            ans.add(new ArrayList(perm));
            return;
        }
        
        for(int i = 0; i < nums.length; ++i){
            if(visited[i] == 1) continue;
            
            // for current level, if the same element has been visited
            // skip the current element
            if(i > 0 && nums[i] == nums[i - 1] && visited[i - 1] != 0) continue;
            
            visited[i] = 1;
            perm.add(nums[i]);
            
            dfs(nums, depth + 1, visited);
            
            perm.remove(perm.size() - 1);
            visited[i] = 0;
        }
    }
```

Similar Problem:

[Leetcode 996. Number of Squareful Arrays](https://leetcode.com/problems/number-of-squareful-arrays/)



[Leetcode 784. Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/)

[Leetcode 22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)

