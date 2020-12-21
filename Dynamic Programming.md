# Dynamic Programming
## Characteristics
### Overlapping Subproblems
Dynamic Programming is mainly used when solutions of same subproblems are needed again and again. In dynamic programming, computed solutions to subproblems are stored in a table so that these don’t have to be recomputed.

### Optimal Substructure Property
A given problems has Optimal Substructure Property if optimal solution of the given problem can be obtained by using optimal solutions of its subproblems. 

## Steps to solve a DP
1. Identify if it is a DP problem
2. Decide a state expression with least parameters(A state can be defined as the set of parameters that can uniquely identify a certain position or standing in the given problem. This set of parameters should be as small as possible to reduce state space)
3. Formulate state relationship    
4. Do tabulation (or add memoization)

[Steps to solve a DP](https://www.geeksforgeeks.org/solve-dynamic-programming-problem/?ref=lbp)

## Knapsack
### 0-1 Knapsack
[youtube](https://www.youtube.com/watch?v=xCbYmUPvc2Q)

```
Question:
给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。其中第i个物品的重量为wt[i]，价值为val[i]，现在让你用这个背包装物品，最多能装的价值是多少？

Example:
N = 3, W = 4
wt = [2, 1, 3]
val = [4, 2, 3] 
```
#### State
Capacity and item

#### DP Array
![](https://github.com/LarryNaaa/Algorithm-Leetcode/blob/master/img/0-1%20Knapsack.png)

The row represents the first 'i' kind of items, 

The column represents the capacity 'j' of knapsack, 

dp[i][j] represents the max profit. 

#### Base Case
When the capacity is zero or we don't have any items to put, the max profit is zero.
dp[0][...] = dp[...][0] = 0

#### Choice
We only have two choices:

Not put item 'i' into knapsack: `dp[i][j] = dp[i - 1][j]`

If we don't choose item 'i', the capacity will not be subtracted, the subproblem is that when capacity is still 'j', we need to get the max profit from first 'i - 1' kinds of items.

Put item 'i' into knapsack: `dp[i][j] = dp[i - 1][j - wt[i - 1]] + val[i - 1]`

If we choose item 'i', the capacity will be subtracted(`j - wt[i - 1]` the current capacity 'j' subtract the weight of item 'i', in item weight array 'wt', item is begin from index 0, so 'i - 1' is the correct index), our current max profit is the sum of the max profit from first 'i - 1' kinds of items(`dp[i - 1][j - wt[i - 1]]`) when capacity is `j - wt[i - 1]` and the profit of current item(`val[i - 1]`).

#### Pseudocode
```Java
for i in [1...N]:
	for j in [1...W]:
		// if the capacity is less than the weight of current item, we cannot choose it
		if (j < wt[i - 1]){
			dp[i][j] = dp[i - 1][j]
		}else{
			// otherwise, we can choose two choices, get the max value of them
			dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i - 1]] + val[i - 1])
		}

return dp[N][W]
```

## Subsequence

[Subsequence](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484666&idx=1&sn=e3305be9513eaa16f7f1568c0892a468&chksm=9bd7faf2aca073e4f08332a706b7c10af877fee3993aac4dae86d05783d3d0df31844287104e&scene=21#wechat_redirect)

### Longest Increasing Subsequence
[Leetcode 300](https://leetcode.com/problems/longest-increasing-subsequence/)

#### Step 1: Identify if it is a DP problem
For example, the sequence is [10,9,2,5,3,7,101,18], in order to get the longest increasing subsequence is [2,3,7,101] which length is 4, we need to get the length of its subproblem([2,3,7]) and then plus 1. So this problem satisfies optimal substructure property, we can solve it by DP.

#### Step 2: Decide a state expression with least parameters and Do tabulation
The state is the index of the input sequence.

dp[i] is the length of the increasing subsequence when index = i. If we know the length of increasing subsequence for each index, we can traverse dp array and find the longest length easily.

base case: dp[0] = 1, when the input sequence only have one parameter, the length of it is 1.

#### Step 3: Formulate state relationship
![Example-1](https://mmbiz.qpic.cn/sz_mmbiz_jpg/gibkIz0MVqdEIkv0ic85dgclViaMQ9IBicMxWQdJu1M3xKrcpc3lxbw7Z9pqhfgXO6gdKw2BxugpUJGJyBToTeYPbA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

In order to find dp[5], we need to find the length of subsequence whose value(nums[index]) is less than nums[5], there are multiple subsequences, we need to find the longest one.

dp[i] = Math.max(dp[i], dp[j] + 1), 'i' is the current index, 'j' is the index of element whose value is less than 'i'.

Pseudocode:
```Java
        // traverse each elements in dp, begin from 1
        for(int i = 1; i < nums.length; i++){
            // traverse each element before 'i', begin from 0
            for(int j = 0; j < i; j++){
                // the val of 'j' < the val of 'i', means that we have a increasing subsquence
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
```

### Maximum Subarray
[Leetcode 53](https://leetcode.com/problems/maximum-subarray/)

### Longest Common Subsequence

[Leetcode 1143](https://leetcode.com/problems/longest-common-subsequence/)

### Edit Distance

[Leetcode 72](https://leetcode.com/problems/edit-distance/)











