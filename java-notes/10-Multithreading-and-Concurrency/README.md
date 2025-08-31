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

[Previous: 09 - IO Streams: Talking to the Outside World](../09-IO-Streams/README.md) | [Next: 11 - The Java Memory Model: A Deep Dive](../11-Java-Memory-Model/README.md)
