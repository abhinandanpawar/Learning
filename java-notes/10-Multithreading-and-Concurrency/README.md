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

### Q33: How can you prevent multiple threads from accessing a resource at the same time?

*   **Simple Answer:** Use the `synchronized` keyword.
*   **Detailed Explanation:**
    *   When multiple threads access and modify the same data, you can get a **race condition** (lost updates, inconsistent reads).
    *   To prevent this, you can make a method `synchronized`. When a thread enters a `synchronized` method on an object, it acquires a "lock". No other thread can enter any `synchronized` method on the *same object* until the first thread is done and releases the lock.
*   **Best Practice:** `synchronized` is a basic tool. For more complex scenarios, you should use the more flexible locking mechanisms in the `java.util.concurrent` package, like `ReentrantLock`.

### Q34: What is the difference between a synchronized method and a synchronized block?

*   **Simple Answer:** A synchronized method locks the entire object. A synchronized block lets you lock only a small part of the code and can lock on a different object.
*   **Detailed Explanation:**
| Feature | Synchronized Method | Synchronized Block |
| :--- | :--- | :--- |
| **Lock Object** | The `this` object. | Any object you specify. |
| **Scope** | The entire method. | Only the code inside the block. |
*   **Key Takeaway:** Synchronized blocks are generally better because you should always try to hold a lock for the shortest possible time. It's also a best practice to use a private lock object (`private final Object lock = new Object();`) instead of locking on `this` to avoid other code from interfering with your locks.

### Q35: What is a deadlock and how do you prevent it?

*   **Simple Answer:** A deadlock is when two or more threads are stuck forever, each waiting for a lock that the other thread holds.
*   **Classic Example:**
    1.  Thread 1 locks A and tries to lock B.
    2.  Thread 2 locks B and tries to lock A.
    3.  Both threads are now stuck.
*   **How to Prevent It:** The easiest way is to enforce a **strict lock ordering**. For example, all threads must agree to always lock A before they lock B. This makes a circular wait impossible.

### Q36: What are the different states of a thread?

*   **Simple Answer:** A thread can be in one of six states: `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, or `TERMINATED`.
*   **Detailed Explanation:**
    *   `NEW`: Created, but `start()` has not been called.
    *   `RUNNABLE`: The thread is running or ready to run.
    *   `BLOCKED`: Waiting to acquire a `synchronized` lock.
    *   `WAITING`: Waiting indefinitely for another thread (e.g., after calling `object.wait()` or `thread.join()`).
    *   `TIMED_WAITING`: Waiting for a specific amount of time (e.g., after calling `Thread.sleep()`).
    *   `TERMINATED`: The thread has finished its work.
*   **Why it's important:** Understanding these states is crucial for debugging concurrency issues with a thread dump.

### Q37: How do you create a thread, and can you restart it?

*   **Simple Answer:** You create a thread by implementing `Runnable` (preferred) or extending `Thread`, and you call the `start()` method. You can **never** restart a thread.
*   **Detailed Explanation:**
    *   **Creating:** The best way is to put your task in a class that implements the `Runnable` interface and pass it to a `Thread`'s constructor.
    *   **Starting:** You must call the `start()` method. This creates a new OS thread and executes the `run()` method. Calling `run()` directly does not create a new thread.
    *   **Restarting:** Once a thread has finished and is in the `TERMINATED` state, it cannot be started again. Calling `start()` a second time will throw an `IllegalThreadStateException`.
*   **Best Practice:** Don't manage threads yourself. Use the **Executor Framework** to manage a thread pool for you.

---

[Previous: 09 - IO Streams: Talking to the Outside World](../09-IO-Streams/README.md) | [Next: 11 - The Java Memory Model: A Deep Dive](../11-Java-Memory-Model/README.md)
