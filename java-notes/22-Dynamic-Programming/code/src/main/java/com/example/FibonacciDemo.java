package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Main application to demonstrate different approaches to solving the Fibonacci sequence.
 * This highlights the core concepts of Dynamic Programming.
 *
 * <p>The naive recursive approach is very inefficient because it re-computes the
 * same subproblems multiple times. The following diagram illustrates the call
 * graph for `fib(5)`, with the repeated computations highlighted:</p>
 * <img src="doc-files/fibonacci_recursion.png" alt="Fibonacci Recursion Call Graph">
 */
public class FibonacciDemo {

    // --- 1. Naive Recursive Approach ---
    // This solution is simple but terribly inefficient due to re-computing the same subproblems.
    // Time Complexity: O(2^n)
    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // --- 2. Top-Down DP with Memoization ---
    // We use a map as a cache to store results of subproblems we've already solved.
    // Time Complexity: O(n), Space Complexity: O(n) for the cache and recursion stack.
    public static long fibonacciMemoized(int n, Map<Integer, Long> memo) {
        if (n <= 1) {
            return n;
        }
        // Check the cache before computing.
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        // If not in cache, compute, store, and then return.
        long fibValue = fibonacciMemoized(n - 1, memo) + fibonacciMemoized(n - 2, memo);
        memo.put(n, fibValue);
        return fibValue;
    }

    // --- 3. Bottom-Up DP with Tabulation ---
    // We build the solution iteratively from the base cases up to n.
    // Time Complexity: O(n), Space Complexity: O(n) for the dp table.
    public static long fibonacciTabulated(int n) {
        if (n <= 1) {
            return n;
        }
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // --- 4. Space-Optimized Bottom-Up DP ---
    // We can optimize the tabulation approach since we only ever need the last two values.
    // Time Complexity: O(n), Space Complexity: O(1)
    public static long fibonacciOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }


    public static void main(String[] args) {
        int n = 40; // Naive recursion becomes very slow around n=40

        System.out.println("--- Dynamic Programming Showcase: Fibonacci Sequence ---");
        System.out.println("Calculating fib(" + n + ")...");

        // --- Memoization Demo ---
        long startTimeMemo = System.nanoTime();
        long resultMemo = fibonacciMemoized(n, new HashMap<>());
        long endTimeMemo = System.nanoTime();
        System.out.println("\n2. Top-Down (Memoization):");
        System.out.println("   Result: " + resultMemo);
        System.out.println("   Time: " + (endTimeMemo - startTimeMemo) / 1_000_000.0 + " ms");

        // --- Tabulation Demo ---
        long startTimeTab = System.nanoTime();
        long resultTab = fibonacciTabulated(n);
        long endTimeTab = System.nanoTime();
        System.out.println("\n3. Bottom-Up (Tabulation):");
        System.out.println("   Result: " + resultTab);
        System.out.println("   Time: " + (endTimeTab - startTimeTab) / 1_000_000.0 + " ms");

        // --- Optimized Demo ---
        long startTimeOpt = System.nanoTime();
        long resultOpt = fibonacciOptimized(n);
        long endTimeOpt = System.nanoTime();
        System.out.println("\n4. Space-Optimized Bottom-Up:");
        System.out.println("   Result: " + resultOpt);
        System.out.println("   Time: " + (endTimeOpt - startTimeOpt) / 1_000_000.0 + " ms");

        // --- Naive Recursive Demo (run last as it's very slow) ---
        System.out.println("\n1. Naive Recursion (be patient...):");
        long startTimeRec = System.nanoTime();
        long resultRec = fibonacciRecursive(n);
        long endTimeRec = System.nanoTime();
        System.out.println("   Result: " + resultRec);
        System.out.println("   Time: " + (endTimeRec - startTimeRec) / 1_000_000.0 + " ms");
    }
}
