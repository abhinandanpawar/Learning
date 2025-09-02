# 24 - Recursion and Backtracking

Recursion and backtracking are powerful problem-solving techniques that are closely related. They are often used to solve problems that involve exploring a set of possibilities.

## Recursion
Recursion is a method of solving a problem where the solution depends on solutions to smaller instances of the same problem. In programming, this is achieved by a function calling itself.

Every recursive function must have two parts:
1.  **Base Case:** A condition that stops the recursion.
2.  **Recursive Step:** The part of the function that calls itself.

## Backtracking
Backtracking is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.

It's a refined form of brute-force search where we "prune" the search space by eliminating paths that we know will not lead to a solution.

## Common Backtracking Problems

### Permutations
The goal is to find all possible orderings of a set of elements.

**The Problem:** Given `[1, 2, 3]`, find all permutations.

**The Backtracking Approach:**
We can build a permutation step by step. For each position, we try to place each available number. We then recursively call the function for the next position. After the recursive call returns, we "backtrack" by undoing the choice we just made, so we can explore other possibilities.

**The Code Example:**
```java
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
```

### Combinations
The goal is to find all possible subsets of a given size.

**The Problem:** Given `n = 4, k = 2`, find all combinations of 2 numbers from `[1, 2, 3, 4]`.

**The Backtracking Approach:**
Similar to permutations, we build a combination step by step. To avoid duplicates and ensure that we are generating combinations (where order doesn't matter), we use a `start` index to ensure that we only consider numbers that are greater than the one we just added.

**The Code Example:**
```java
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), n, k, 1);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int n, int k, int start){
        if(tempList.size() == k){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = start; i <= n; i++){
                tempList.add(i);
                backtrack(list, tempList, n, k, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
```
This backtracking template is very powerful and can be adapted to solve many other problems like Generate Parentheses, Subsets, and Combination Sum.
