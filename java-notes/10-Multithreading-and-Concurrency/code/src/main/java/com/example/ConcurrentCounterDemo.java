package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main application to demonstrate concurrency problems and solutions.
 *
 * <p>A Java thread can exist in one of several states. The following state diagram
 * illustrates the lifecycle of a thread:</p>
 * <img src="doc-files/thread_lifecycle.png" alt="Thread Lifecycle Diagram">
 */
public class ConcurrentCounterDemo {

    // Your Mission:
    // The UnsafeCounter is not thread-safe, leading to a race condition.
    // Your mission is to fix it using the best tool for the job.
    // 1. Change the 'count' field from 'int' to 'java.util.concurrent.atomic.AtomicInteger'.
    // 2. Initialize it to a new AtomicInteger in the declaration.
    // 3. Modify the 'increment()' method to use the atomic 'incrementAndGet()' method.
    // 4. Modify the 'getCount()' method to use the 'get()' method.
    static class UnsafeCounter {
        private int count = 0;

        public void increment() {
            // This operation is NOT atomic. It involves three steps:
            // 1. Read the current value of count.
            // 2. Add 1 to the value.
            // 3. Write the new value back to count.
            // A race condition can occur if multiple threads interleave these steps.
            count++;
        }

        public int getCount() {
            return count;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Concurrency Showcase: Race Conditions ---");

        int tasks = 1000;
        int incrementsPerTask = 1000;
        int expectedResult = tasks * incrementsPerTask;

        // --- Demonstrate the Race Condition (and then fix it!) ---
        System.out.println("\n--- Testing the Counter with multiple threads... ---");
        UnsafeCounter counter = new UnsafeCounter();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < tasks; i++) {
            executor.submit(() -> {
                for (int j = 0; j < incrementsPerTask; j++) {
                    counter.increment();
                }
            });
        }

        // Shutdown the executor and wait for tasks to complete
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Expected result: " + expectedResult);
        System.out.println("Actual result:   " + counter.getCount() + " (If this is not " + expectedResult + ", your mission is to fix the UnsafeCounter!)");
    }
}
