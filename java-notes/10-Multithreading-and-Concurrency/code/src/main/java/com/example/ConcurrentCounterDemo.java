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

    // A simple, non-thread-safe integer counter.
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

    // A thread-safe counter using AtomicInteger.
    static class SafeCounter {
        // AtomicInteger provides atomic operations like incrementAndGet().
        // These operations are guaranteed to be performed as a single, indivisible unit.
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Concurrency Showcase: Race Conditions ---");

        int tasks = 1000;
        int incrementsPerTask = 1000;
        int expectedResult = tasks * incrementsPerTask;

        // --- 1. Demonstrate the Race Condition ---
        System.out.println("\n1. Testing UnsafeCounter with multiple threads...");
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        ExecutorService unsafeExecutor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < tasks; i++) {
            unsafeExecutor.submit(() -> {
                for (int j = 0; j < incrementsPerTask; j++) {
                    unsafeCounter.increment();
                }
            });
        }

        // Shutdown the executor and wait for tasks to complete
        unsafeExecutor.shutdown();
        unsafeExecutor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Expected result: " + expectedResult);
        System.out.println("UnsafeCounter result: " + unsafeCounter.getCount() + " (Result will likely be incorrect!)");


        // --- 2. Demonstrate the Thread-Safe Solution ---
        System.out.println("\n2. Testing SafeCounter with multiple threads...");
        SafeCounter safeCounter = new SafeCounter();
        ExecutorService safeExecutor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < tasks; i++) {
            safeExecutor.submit(() -> {
                for (int j = 0; j < incrementsPerTask; j++) {
                    safeCounter.increment();
                }
            });
        }

        safeExecutor.shutdown();
        safeExecutor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Expected result: " + expectedResult);
        System.out.println("SafeCounter result: " + safeCounter.getCount() + " (Result will be correct)");
    }
}
