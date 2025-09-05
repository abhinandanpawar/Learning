# 22 - Dynamic Programming: Solving Problems by Remembering the Past

Dynamic Programming (DP) is a powerful algorithmic technique for solving complex problems by breaking them down into simpler, overlapping subproblems. The core idea is simple: **solve each subproblem only once and store its result.** When you encounter the same subproblem again, you just look up the answer instead of re-computing it.

**What's in this chapter:**
*   [The Core Idea: When Can We Use DP?](#1-the-core-idea-when-can-we-use-dp)
*   [The Two Approaches: Memoization vs. Tabulation](#2-the-two-approaches-memoization-vs-tabulation)
*   [A Framework for Solving DP Problems](#3-a-framework-for-solving-dp-problems)
*   [Hands-On Lab: The Fibonacci Sequence](#4-hands-on-lab-the-fibonacci-sequence)
*   [Classic DP Problems (Exercises)](#5-classic-dp-problems-exercises)

---

## 1. The Core Idea: When Can We Use DP?

A problem can be solved with Dynamic Programming if it has two key properties:

1.  **Overlapping Subproblems:** The problem can be broken down into smaller subproblems that are reused multiple times. For example, to calculate `fib(5)`, you need `fib(4)` and `fib(3)`. To calculate `fib(4)`, you need `fib(3)` and `fib(2)`. The `fib(3)` subproblem is overlapping.

```mermaid
graph TD
    subgraph "fib(5)"
        fib5("fib(5)") --> fib4("fib(4)")
        fib5 --> fib3("fib(3)")
        fib4 --> fib3_2("fib(3)")
        fib4 --> fib2("fib(2)")
        fib3 --> fib2_2("fib(2)")
        fib3 --> fib1("fib(1)")
        fib3_2 --> fib2_3("fib(2)")
        fib3_2 --> fib1_2("fib(1)")
    end
    style fib3 fill:#ffcdd2,stroke:#c62828
    style fib3_2 fill:#ffcdd2,stroke:#c62828
    note "The fib(3) subproblem is computed twice."
```

2.  **Optimal Substructure:** The optimal solution to the overall problem can be constructed from the optimal solutions of its subproblems.

---

## 2. The Two Approaches: Memoization vs. Tabulation

There are two standard ways to implement a DP solution. We will use the Fibonacci sequence as our running example.

### a. Top-Down with Memoization
This approach is essentially a "smarter" version of a recursive solution. You write the logic recursively, but you maintain a cache (like a map or an array) to store the results of subproblems. Before computing, you check the cache. If the result is there, you return it. If not, you compute it, store it in the cache, and then return it.

**Analogy:** Doing your math homework, but keeping a notebook of solved problems. When you see a problem you've already solved, you just copy the answer from your notebook.

### b. Bottom-Up with Tabulation
This approach solves the problem iteratively, starting from the smallest subproblems and building up to the desired solution. You typically use an array or a 2D table (the "tabulation") to store the results of subproblems.

**Analogy:** Building a skyscraper. You start with the foundation (the base cases), then build the first floor, then the second, and so on, until you reach the top. Each new floor relies on the one below it being complete.

---

## 3. A Framework for Solving DP Problems

When you encounter a new DP problem, follow these steps:
1.  **Identify the State:** What is the minimum set of variables needed to define a subproblem? (e.g., for Fibonacci, the state is just the number `n`). This will define the dimensions of your DP table/cache.
2.  **Define the Recurrence Relation:** How can you solve a problem of state `S` using the solutions to smaller subproblems? (e.g., `fib(n) = fib(n-1) + fib(n-2)`).
3.  **Identify the Base Cases:** What are the smallest subproblems that you can solve directly, without recursion? (e.g., `fib(0) = 0`, `fib(1) = 1`).

---

## 4. Hands-On Lab: The Fibonacci Sequence

To see the difference between these approaches, we've created a runnable project in the `code/` directory that calculates the Nth Fibonacci number using:
1.  Naive Recursion (to show the problem)
2.  Top-Down DP with Memoization
3.  Bottom-Up DP with Tabulation
4.  Space-Optimized Bottom-Up DP

**To run it:**
1.  Navigate to the `code/` directory.
2.  Run `mvn compile exec:java`.
3.  Explore the source code to see how the four different approaches are implemented and how their performance compares.

---

## 5. Classic DP Problems (Exercises)

Now that you have a framework, try applying it to these classic problems. The original `README` for this chapter contained solutions; try to solve them on your own first, then compare your approach.

*   **Maximum Subarray (Kadane's Algorithm):** Find the contiguous subarray within an array that has the largest sum.
*   **Coin Change:** Find the fewest number of coins needed to make up a certain amount.
*   **Longest Increasing Subsequence:** Find the length of the longest subsequence where elements are in increasing order.
*   **Edit Distance:** Find the minimum number of operations (insert, delete, replace) to transform one string into another.
