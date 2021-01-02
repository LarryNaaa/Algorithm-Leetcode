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

## Subsequence([Subsequence](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484666&idx=1&sn=e3305be9513eaa16f7f1568c0892a468&chksm=9bd7faf2aca073e4f08332a706b7c10af877fee3993aac4dae86d05783d3d0df31844287104e&scene=21#wechat_redirect))

### Longest Increasing Subsequence([Leetcode 300](https://leetcode.com/problems/longest-increasing-subsequence/))
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

### Maximum Subarray([Leetcode 53](https://leetcode.com/problems/maximum-subarray/))


### Longest Common Subsequence([Leetcode 1143](https://leetcode.com/problems/longest-common-subsequence/))



### Edit Distance([Leetcode 72](https://leetcode.com/problems/edit-distance/))

#### 1. State

The states are the index of these two given string.

#### 2. DP Array

`dp[i][j]`: the minimum number of operations for string1[0...i] and string2[0...j].

base case: `dp[i][0] = i` and  `dp[0][j] = j`.

***\*Example :\****

***\*Input: text1 = "abcde", text2 = "ace"\**** 

|         |  ""  | "r"  | "ro" | "ros" |
| :-----: | :--: | :--: | :--: | :---: |
|   " "   |  0   |  1   |  2   |   3   |
|   "h"   |  1   |      |      |       |
|  "ho"   |  2   |      |      |       |
|  "hor"  |  3   |      |      |       |
| "hors"  |  4   |      |      |       |
| "horse" |  5   |      |      |       |

#### 3. State Relationship

For each string1[i] and string2[j], we have 4 operations:

1. If string1[i] == string2[j], we don't need to do any operations, they already equal to each other :

   |      |  ""  | "h"  |
   | :--: | :--: | :--: |
   |  ""  |  0   |  1   |
   | "h"  |  1   |  0   |

    the number of operations in this situation is as same as the number of operations when i - 1 and j - 1:

   ```java
   dp[i][j] = dp[i - 1][j - 1];
   ```

   

2. Otherwise we have three operations for each string1[i] and string2[j]:

   a. replace

   |      |  “”  | “r”  |
   | :--: | :--: | :--: |
   |  “”  |  0   |  1   |
   | “h”  |  1   |  1   |

   we need to replace the character in string2 with the character in string1, it means that we will do one more replace operation than `dp[i - 1][j - 1]`:

   ```java
   dp[i][j] = dp[i - 1][j - 1] + 1;
   ```

   b. insert

   |      |  “”  | “r”  | “ro” |
   | :--: | :--: | :--: | :--: |
   |  “”  |  0   |  1   |  2   |
   | “h”  |  1   |  1   |  2   |

   after we replace "h" in string1 with "r", in order to get "ro", we need to insert a character 'o', so we will do one more insert operation than `dp[i][j - 1]`:

   ```java
   dp[i][j] = dp[i][j - 1] + 1;
   ```

   c. delete

   |      |  “”  | “r”  |
   | :--: | :--: | :--: |
   |  “”  |  0   |  1   |
   | “h”  |  1   |  1   |
   | “ho” |  2   |  2   |

   after we replace "h" in string1 with "r", now our string1 is "ro", in order to get "r", we need to delete a character 'o', so we will do one more delete operation than `dp[i - 1][j]`:

   ```java
   dp[i][j] = dp[i - 1][j] + 1;
   ```

   in this situation, in order to get the minimum number of operations, we need to get the minimum value of these three operations. 

#### 4. Pseudocode

```java
        // dp
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // initial dp
        for(int i = 0; i <= word1.length(); i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= word2.length(); j++){
            dp[0][j] = j;
        }
        // traverse i
        for(int i = 1; i <= word1.length(); i++){
            // traverse j
            for(int j = 1; j <= word2.length(); j++){
                // the characters at index i and j are euqal
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    // skip: dp[i - 1][j - 1]
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    // insert: dp[i][j - 1] + 1
                    // replace: dp[i - 1][j - 1] + 1
                    // delete: dp[i - 1][j] + 1
                    dp[i][j] = 
                        Math.min(
                        Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1), 
                        dp[i][j] = dp[i - 1][j] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
```

### Delete Operation for Two Strings([Leetcode 583](https://leetcode.com/problems/delete-operation-for-two-strings/))



### Russian Doll Envelopes([Leetcode 354](https://leetcode.com/problems/russian-doll-envelopes/))



## Greedy

### Interval scheduling

#### Non-overlapping Intervals([Leetcode 435](https://leetcode.com/problems/non-overlapping-intervals/))

#### Minimum Number of Arrows to Burst Balloons([Leetcode 452](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/))

```java
// increasing sort
Arrays.sort(points, new Comparator<int[]>(){
           public int compare(int[] arr1, int[] arr2){
               return (arr1[1] < arr2[1]) ? -1 : ((arr1[1] == arr2[1]) ? 0 : 1);
           } 
        });
```

### Jump Game

#### Jump Game([Leetcode 55](https://leetcode.com/problems/jump-game/))

#### Jump Game II([Leetcode 45](https://leetcode.com/problems/jump-game-ii/))



## Other
### Regular Expression Matching([Leetcode 10](https://leetcode.com/problems/regular-expression-matching/))

### Burst Balloons([Leetcode 312](https://leetcode.com/problems/burst-balloons/))

### 4 Keys Keyboard([Leetcode 651](https://leetcode.com/problems/4-keys-keyboard/))



## Stock Problem
### Best Time to Buy and Sell Stock([Leetcode 121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/))
### Best Time to Buy and Sell Stock II([Leetcode 122](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/))
### Best Time to Buy and Sell Stock with Cooldown([Leetcode 309](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/))
### Best Time to Buy and Sell Stock with Transaction Fee([Leetcode 714](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/))
### Best Time to Buy and Sell Stock III([Leetcode 123](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/))
### Best Time to Buy and Sell Stock IV([Leetcode 188](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/))







