# 22 - Dynamic Programming

Dynamic Programming (DP) is a powerful technique for solving problems by breaking them down into simpler subproblems. It is typically used for optimization problems where we are looking for the best solution from a set of possible solutions.

## Introduction to Dynamic Programming
The core idea of DP is to solve each subproblem only once and store its result. When the same subproblem is encountered again, we can simply look up its result instead of re-computing it. This is known as **memoization**.

There are two key properties that a problem must have for DP to be applicable:
1.  **Overlapping Subproblems:** The problem can be broken down into subproblems that are reused several times.
2.  **Optimal Substructure:** The optimal solution to the overall problem can be constructed from the optimal solutions of its subproblems.

## Classic DP Problems

### Maximum Subarray
The goal is to find the contiguous subarray within an array that has the largest sum.

**The Problem:** Given an array `[−2, 1, −3, 4, −1, 2, 1, −5, 4]`, the contiguous subarray `[4, −1, 2, 1]` has the largest sum = 6.

**The DP Approach (Kadane's Algorithm):**
We can solve this in linear time by iterating through the array and keeping track of the maximum sum of a subarray ending at the current position.

Let `dp[i]` be the maximum sum of a subarray ending at index `i`. The recurrence relation is:
`dp[i] = max(nums[i], dp[i-1] + nums[i])`

This means the maximum sum ending at `i` is either the element at `i` itself, or the element at `i` plus the maximum sum ending at the previous position.

**The Code Example:**
```java
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
```

### Coin Change
The goal is to find the fewest number of coins that you need to make up a certain amount. You are given coins of different denominations and a total amount.

**The Problem:** Given `coins = [1, 2, 5]` and `amount = 11`, the fewest number of coins is 3 (5 + 5 + 1).

**The DP Approach:**
Let `dp[i]` be the minimum number of coins required to make up amount `i`.
The recurrence relation is:
`dp[i] = min(dp[i], dp[i - coin] + 1)` for each coin in `coins`.

**The Code Example:**
```java
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with a value larger than any possible number of coins
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```
This optimized solution uses constant space by only keeping track of the `maxEndingHere` and the overall `maxSoFar`.

### Longest Increasing Subsequence
The goal is to find the length of the longest subsequence of a given sequence in which the subsequence's elements are in sorted order, from smallest to largest.

**The Problem:** Given `[10, 9, 2, 5, 3, 7, 101, 18]`, the longest increasing subsequence is `[2, 3, 7, 101]`, so the length is 4.

**The DP Approach:**
Let `dp[i]` be the length of the longest increasing subsequence ending at index `i`. The recurrence relation is:
`dp[i] = 1 + max(dp[j])` for all `j < i` where `nums[j] < nums[i]`.

**The Code Example (O(n^2)):**
```java
public class LIS {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
```
There is also a more optimized O(n log n) solution that uses a patient sorting approach, but the O(n^2) DP solution is a common starting point in interviews.

### Edit Distance
The goal is to find the minimum number of operations (insert, delete, replace) required to transform one string into another.

**The Problem:** Find the edit distance between "horse" and "ros".

**The DP Approach:**
Let `dp[i][j]` be the edit distance between the first `i` characters of `word1` and the first `j` characters of `word2`.

The recurrence relation is:
*   If `word1[i] == word2[j]`, then `dp[i][j] = dp[i-1][j-1]`.
*   If `word1[i] != word2[j]`, then `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`. The three options correspond to delete, insert, and replace operations.

**The Code Example:**
```java
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[len1][len2];
    }
}
```
