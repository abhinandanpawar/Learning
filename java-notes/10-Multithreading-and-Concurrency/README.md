# 10 - Multithreading and Concurrency: Juggling Multiple Tasks

From the very beginning, we designed Java to be a concurrent language. We knew that to build modern, responsive applications, you would need a way to do multiple things at once.

## 1. Threads: The Units of Concurrency

A thread is a single path of execution within a program. Each thread has its own stack, but shares the heap with other threads in the same process. This is the fundamental model of concurrency that we built into the JVM.

## 2. The `synchronized` Keyword: Our First Concurrency Primitive

When multiple threads share data, you can get into trouble. We needed a simple way to prevent threads from interfering with each other. Our solution was the `synchronized` keyword.

When a thread enters a `synchronized` method or block, it acquires a "lock" on the object. No other thread can enter a `synchronized` block on the same object until the lock is released.

This was our simple, low-level primitive for thread safety.

## 3. The Java Memory Model: The Rules of the Road

We had to define a clear set of rules for how threads interact with memory. This is the Java Memory Model (JMM). The JMM guarantees that when a thread exits a `synchronized` block, all of its changes to shared variables are visible to other threads that later enter a `synchronized` block on the same object.

The JMM is a complex topic, but the key takeaway is that it provides the guarantees you need to write correct concurrent programs.

## 4. The `java.util.concurrent` Package: A Higher-Level API

While `synchronized` is powerful, it's also a bit low-level. In Java 5, we introduced the `java.util.concurrent` package, which provides a rich set of higher-level concurrency utilities.

**System Design Insight:** The Executor Framework, part of this package, is a prime example of good system design. It decouples the submission of tasks from the execution of tasks. You can submit your tasks to an `ExecutorService`, and it will manage a pool of threads to execute them. This is a much more efficient and manageable way to handle threads than creating them manually.

## 5. Our E-commerce App: Processing Orders Concurrently

In our e-commerce app, we could use a thread pool to process incoming orders concurrently.

```java
ExecutorService executor = Executors.newFixedThreadPool(10);

while (true) {
    Order order = getNextOrder();
    executor.execute(() -> processOrder(order));
}
```

This would allow us to process up to 10 orders at the same time, making our application much more scalable.

---

## Interview Deep Dives

### Q44 & Q82: How can we ensure that a resource isn't used by multiple threads simultaneously?

To understand this, let's look at an example of a simple counter that is accessed by multiple threads.

**The Code Example:**
```java
public class Counter {
    private int count = 0;

    // This method is not thread-safe.
    public void increment() {
        count++;
    }

    // This method is thread-safe because of the 'synchronized' keyword.
    public synchronized void incrementAndMakeSafe() {
        count++;
    }
}
```

**Detailed Explanation:**
The `increment()` method is not thread-safe. The operation `count++` is not atomic; it's actually three separate operations: read the value of `count`, increment it, and write the new value back. If two threads execute this method at the same time, they might both read the same value, increment it, and then write back the same result, leading to a lost update. This is called a **race condition**.

To solve this, we use the `synchronized` keyword.
*   When a method is declared `synchronized`, a thread must acquire the **intrinsic lock** (also called a "monitor lock") of the object before it can execute the method.
*   Only one thread can hold the lock at a time. Any other thread that tries to call a `synchronized` method on the *same object* will be blocked until the lock is released.
*   The lock is automatically released when the thread exits the method.

**The Principal's Take:**
*   **System Design:** `synchronized` is the most basic mechanism for enforcing mutual exclusion in Java. However, it's a blunt instrument. It's easy to use for simple cases, but it can lead to performance bottlenecks and deadlocks in complex systems.
*   **Best Practice:** For anything non-trivial, you should prefer the higher-level concurrency utilities in the `java.util.concurrent.locks` package, such as `ReentrantLock`. A `ReentrantLock` provides the same mutual exclusion guarantees as `synchronized`, but it's more flexible. It can be interrupted, you can try to acquire it with a timeout, and it provides more visibility into its state.

---

### Q58: Describe the different states of a thread.

A thread can be in one of six states, which are defined in the `Thread.State` enum.

**The States:**

1.  **`NEW`**: The thread has been created but has not yet started (i.e., `start()` has not been called).
2.  **`RUNNABLE`**: The thread is either currently running on the CPU, or it's ready to run and is waiting for the OS scheduler to assign it a time slice.
3.  **`BLOCKED`**: The thread is waiting to acquire an intrinsic lock (i.e., it's waiting to enter a `synchronized` block).
4.  **`WAITING`**: The thread is waiting indefinitely for another thread to perform a particular action. This happens when you call `Object.wait()`, `Thread.join()`, or `LockSupport.park()`.
5.  **`TIMED_WAITING`**: The thread is waiting for a specified amount of time for another thread to perform an action. This happens when you call methods with a timeout, like `Thread.sleep()`, `Object.wait(timeout)`, or `Thread.join(timeout)`.
6.  **`TERMINATED`**: The thread has completed its execution.

**The Principal's Take:**
*   **Debugging & Profiling:** Understanding these states is crucial for debugging concurrency issues. When you take a **thread dump** (using `jstack` or a profiler), each thread will be listed with its current state. If you have a performance issue, you might find many threads in the `BLOCKED` state, which indicates lock contention. If your application seems stuck, you might find threads in a `WAITING` state, which could indicate a deadlock or a missed `notify()` call.

---

[Previous: 09 - IO Streams: Talking to the Outside World](../09-IO-Streams/README.md) | [Next: 11 - The Java Memory Model: A Deep Dive](../11-Java-Memory-Model/README.md)
