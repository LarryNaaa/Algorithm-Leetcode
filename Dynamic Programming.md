# Dynamic Programming
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
