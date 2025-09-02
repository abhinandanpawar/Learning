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

### Q: What is the difference between a synchronized method and a synchronized block?

This question tests your understanding of how to use the `synchronized` keyword effectively.

**The Principal's Take:** Synchronized blocks are more flexible and are often preferred over synchronized methods because they allow you to lock on any object and to scope the synchronization to the smallest possible block of code.

| Feature | Synchronized Method | Synchronized Block |
|---|---|---|
| **Lock Object** | The lock is on the `this` object (for instance methods) or the `Class` object (for static methods). | You can explicitly specify the object to lock on. |
| **Scope** | The entire method is synchronized. | Only the code within the block is synchronized. |
| **Flexibility** | Less flexible. | More flexible. Allows for finer-grained locking. |

**The Code Example:**
```java
public class SynchronizationComparison {

    // Synchronized method: locks on 'this'
    public synchronized void synchronizedMethod() {
        // ...
    }

    // Synchronized block: locks on 'this'
    public void synchronizedBlockOnThis() {
        synchronized (this) {
            // ...
        }
    }

    private final Object lock = new Object();

    // Synchronized block: locks on a private lock object
    public void synchronizedBlockOnPrivateLock() {
        synchronized (lock) {
            // ...
        }
    }
}
```

**System Design Insight:**
*   **Minimize the scope of synchronization:** You should only synchronize the critical section of your code that accesses shared data. Synchronizing entire methods can lead to performance bottlenecks, as it holds the lock for longer than necessary. This is a strong argument for preferring synchronized blocks.
*   **Avoid locking on `this`:** Locking on `this` (which is what a synchronized method does implicitly) can be risky because it exposes the lock to external code. Another class could acquire a lock on your object and cause a deadlock. It is a best practice to use a `private final Object` as your lock object. This encapsulates the lock within your class and prevents external interference.

---

### Q: What is a deadlock and how can you prevent it?

A deadlock is a situation where two or more threads are blocked forever, waiting for each other.

**The Principal's Take:** Deadlocks are one of the most feared concurrency issues. They can be very hard to debug because they often depend on timing and are not easily reproducible. The best way to deal with deadlocks is to design your code to prevent them from happening in the first place.

**A Classic Deadlock Scenario:**
Imagine two threads, Thread 1 and Thread 2, and two resources, Lock A and Lock B.
1.  Thread 1 acquires Lock A.
2.  Thread 2 acquires Lock B.
3.  Thread 1 tries to acquire Lock B, but it's held by Thread 2, so Thread 1 blocks.
4.  Thread 2 tries to acquire Lock A, but it's held by Thread 1, so Thread 2 blocks.

Now, both threads are blocked, waiting for a lock held by the other thread. They will wait forever.

**How to Prevent Deadlocks:**
The most common way to prevent deadlocks is to ensure that all threads acquire locks in the same order.

**The Code Example:**
```java
public class DeadlockExample {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void method1() {
        synchronized (lockA) {
            // ...
            synchronized (lockB) {
                // ...
            }
        }
    }

    public void method2() {
        // This can cause a deadlock if called concurrently with method1
        // synchronized (lockB) {
        //     // ...
        //     synchronized (lockA) {
        //         // ...
        //     }
        // }

        // This is the correct way: acquire locks in the same order as method1
        synchronized (lockA) {
            // ...
            synchronized (lockB) {
                // ...
            }
        }
    }
}
```

**System Design Insight:**
*   **Lock Ordering:** Enforcing a strict order for acquiring locks is the simplest and most effective way to prevent deadlocks.
*   **Timeouts:** Using `tryLock` with a timeout (from the `java.util.concurrent.locks.Lock` interface) can be another way to mitigate deadlocks. If a thread can't acquire a lock within a certain amount of time, it can back off and try again later.
*   **Deadlock Detection:** The JVM is not able to prevent deadlocks, but it can detect them. You can use a tool like `jstack` or a profiler to get a thread dump, which will show you if any threads are in a deadlock state.

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

### Q36, Q37, Q69, Q81: How do you create a thread? Can you restart a dead thread?

To understand this, let's look at the two traditional ways to create a thread and its lifecycle.

**The Code Example:**
```java
// Option 1: Implement the Runnable interface (Preferred)
class MyTask implements Runnable {
    @Override
    public void run() { // This method is the entry point for the new thread.
        System.out.println("Hello from a Runnable!");
    }
}

// Option 2: Extend the Thread class
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from a Thread!");
    }
}

public class ThreadCreationExample {
    public static void main(String[] args) throws InterruptedException {
        // Create and start a thread using a Runnable
        Thread t1 = new Thread(new MyTask());
        t1.start(); // This moves the thread from NEW to RUNNABLE state

        // Create and start a thread by extending Thread
        Thread t2 = new MyThread();
        t2.start();

        // Wait for the threads to finish
        t1.join();
        t2.join();

        // Q69: Can a dead thread be started again?
        System.out.println("t1 state after completion: " + t1.getState()); // TERMINATED
        // t1.start(); // This would throw an IllegalThreadStateException
    }
}
```

**Detailed Explanation:**
There are two ways to define the work that a thread will do:
1.  **Implement `Runnable`:** You create a class that implements the `Runnable` interface. This interface has a single method, `run()`. This is the preferred approach because it separates the task (the `Runnable`) from the execution mechanism (the `Thread` object).
2.  **Extend `Thread`:** You create a class that extends `Thread` and overrides its `run()` method. This is less flexible, as your class cannot extend any other class.

In both cases, you **must** call the `start()` method to create a new OS-level thread and have it execute the `run()` method. You should never call `run()` directly.

**The Principal's Take:**
*   **Lifecycle:** A thread can only be started once. After it completes its work and its `run()` method returns, it enters the `TERMINATED` state. You **cannot** restart a dead thread. Attempting to call `start()` on a terminated thread will result in an `IllegalThreadStateException`.
*   **Best Practice:** As we discussed earlier in this guide, in modern applications, you should **almost never** manage threads directly by creating `Thread` objects. You should always use an `ExecutorService` to manage a pool of threads for you. This is more efficient, more robust, and provides better control over your application's resources. The `Runnable` task you create, however, is still the same.

---

[Previous: 09 - IO Streams: Talking to the Outside World](../09-IO-Streams/README.md) | [Next: 11 - The Java Memory Model: A Deep Dive](../11-Java-Memory-Model/README.md)
