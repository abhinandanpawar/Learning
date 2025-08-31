# 10 - Multithreading and Concurrency in Java

Multithreading is a Java feature that allows concurrent execution of two or more parts of a program for maximum utilization of the CPU.

## 1. What is a Thread?

A thread is a lightweight subprocess, the smallest unit of processing.

## 2. Creating Threads

There are two ways to create a thread:

*   **Extending the `Thread` class:**

    ```java
    class MyThread extends Thread {
        public void run() {
            System.out.println("Thread is running.");
        }
    }

    // ...
    MyThread t1 = new MyThread();
    t1.start();
    ```

*   **Implementing the `Runnable` interface:** (Recommended)

    ```java
    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Thread is running.");
        }
    }

    // ...
    Thread t1 = new Thread(new MyRunnable());
    t1.start();
    ```

## 3. Thread Synchronization

When multiple threads access a shared resource, you need to ensure that only one thread can access the resource at a time. This is called synchronization.

The `synchronized` keyword can be used to create a synchronized block or method.

```java
class Counter {
    private int count = 0;

    // Synchronized method
    public synchronized void increment() {
        count++;
    }
}
```

## 4. The `volatile` Keyword

The `volatile` keyword is used to mark a Java variable as "being stored in main memory". It ensures that any thread that reads the variable will see the most recently written value.

```java
private volatile boolean running = true;
```

## 5. The Executor Framework

The Executor Framework, part of the `java.util.concurrent` package, provides a higher-level API for managing threads. It decouples task submission from the mechanics of how each task will be run, including details of thread use, scheduling, etc.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ...

ExecutorService executor = Executors.newFixedThreadPool(5);

for (int i = 0; i < 10; i++) {
    Runnable worker = new WorkerThread("" + i);
    executor.execute(worker);
}

executor.shutdown();
```

---

[Previous: 09 - IO Streams](../09-IO-Streams/README.md) | [Next: 11 - Java Memory Model](../11-Java-Memory-Model/README.md)
