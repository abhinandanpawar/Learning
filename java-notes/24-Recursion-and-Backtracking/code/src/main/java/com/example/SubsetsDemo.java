package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Main application to demonstrate backtracking by generating all subsets of a set.
 */
public class SubsetsDemo {

    /**
     * Public-facing method to start the process.
     * @param nums The input array of unique integers.
     * @return A list of all possible subsets.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();
        // Start the backtracking process from the first element (index 0).
        backtrack(allSubsets, currentSubset, nums, 0);
        return allSubsets;
    }

    /**
     * The core backtracking method.
     * @param allSubsets The master list to store all completed subsets.
     * @param currentSubset The subset we are currently building.
     * @param nums The original input numbers.
     * @param start The index in 'nums' to start considering elements from.
     */
    private void backtrack(List<List<Integer>> allSubsets, List<Integer> currentSubset, int[] nums, int start) {
        // First, add the current combination as a valid subset.
        // This includes the empty set on the first call.
        allSubsets.add(new ArrayList<>(currentSubset));

        // Explore choices by iterating through the remaining elements.
        for (int i = start; i < nums.length; i++) {
            // 1. CHOOSE: Add the current element to our subset.
            currentSubset.add(nums[i]);

            // 2. EXPLORE: Recursively call backtrack for the next elements.
            // We pass 'i + 1' to ensure we don't reuse the same element
            // and to avoid duplicate subsets (e.g., {1, 2} is the same as {2, 1}).
            backtrack(allSubsets, currentSubset, nums, i + 1);

            // 3. UN-CHOOSE (Backtrack): Remove the element we just added,
            // so we can explore the path where this element was not chosen.
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Backtracking Showcase: All Subsets ---");
        SubsetsDemo demo = new SubsetsDemo();
        int[] nums = {1, 2, 3};

        System.out.println("Generating all subsets for {1, 2, 3}:");
        List<List<Integer>> result = demo.subsets(nums);

        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}
