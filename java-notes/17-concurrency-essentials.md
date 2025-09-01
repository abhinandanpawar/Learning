# 17 - Concurrency Essentials: A Principal Engineer's Guide

Concurrency in Java is not just about `Thread` and `synchronized`. Modern Java provides a rich toolkit for building high-performance, scalable systems. This guide dives into the key components, explaining them from a systems-level perspective.

---

## 1. `ExecutorService`: The Foundation of Modern Concurrency

**Never create raw threads (`new Thread()`) in a production application.** This is a principal-level rule. Raw threads don't have connection pooling, backpressure, or proper lifecycle management, and can easily lead to `OutOfMemoryError` if you create too many.

**System Design Context:**
In any real-world service (e.g., a web server, a message processor), you need to manage a pool of threads to handle incoming tasks. An `ExecutorService` gives you a managed pool of threads, decoupling task submission from task execution. This is the foundation of building responsive and resilient services.

### Complete, Runnable Example:
This example shows a simple web server that offloads request processing to a fixed-size thread pool.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebServer {

    private final ExecutorService pool;

    public WebServer(int poolSize) {
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleRequest(int requestId) {
        pool.submit(() -> {
            System.out.println("Processing request " + requestId + " on thread " + Thread.currentThread().getName());
            try {
                // Simulate work
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void shutdown() throws InterruptedException {
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting web server...");
        WebServer server = new WebServer(10);
        for (int i = 0; i < 100; i++) {
            server.handleRequest(i);
        }
        server.shutdown();
        System.out.println("Server shutdown complete.");
    }
}
```

### Memory Flow & JVM Deep Dive:
```
// 1. WebServer object is created.
//    - The `pool` variable holds a reference to a `ThreadPoolExecutor` object on the HEAP.
//    - The `ThreadPoolExecutor` object itself manages an array of `Thread` objects and a `BlockingQueue` for tasks.
//
//    STACK (main)         HEAP
//    +-----------+        +--------------------------+
//    | server    |------> | WebServer Object         |
//    +-----------+        |   pool ------------------+-->+---------------------------+
//                         +--------------------------+   | ThreadPoolExecutor Object |
//                                                        |   - Thread[] workerThreads|
//                                                        |   - BlockingQueue tasks   |
//                                                        +---------------------------+
//
// 2. handleRequest() is called.
//    - A lambda is created for the task. This lambda object is placed on the HEAP.
//    - The `submit()` method places this lambda object into the `tasks` queue in the HEAP.
//
// 3. A worker thread from the pool takes the task.
//    - The worker thread executes the `run()` method of the lambda.
//    - A new stack frame is created on the *worker thread's own stack* for the execution of the lambda.
```

### Production-Oriented Advice & Trade-offs:
*   **Sizing Your Pool:** For CPU-bound tasks, a pool size equal to `Runtime.getRuntime().availableProcessors()` is a good starting point. For I/O-bound tasks, the pool can be much larger (e.g., 50-100), as the threads will spend most of their time waiting.
*   **Monitoring:** Use a `ThreadPoolExecutor` directly to get more control and monitoring capabilities (e.g., getting the queue size, number of active threads). You can expose these metrics via JMX or Micrometer.
*   **Bounded Queues:** The `newFixedThreadPool` uses an *unbounded* `LinkedBlockingQueue`. If tasks arrive faster than they can be processed, this can lead to an `OutOfMemoryError`. For more resilient systems, consider creating a `ThreadPoolExecutor` with a *bounded* queue and a `RejectedExecutionHandler`.

---

## 2. `CompletableFuture`: For Asynchronous, Non-blocking I/O

**System Design Context:**
This is the workhorse of modern, I/O-bound microservices. When your service needs to call another service (e.g., a REST API, a database), you cannot afford to have your main request-handling thread block and wait. `CompletableFuture` lets you write non-blocking code that is highly scalable.

### Complete, Runnable Example:
This example shows a service that fetches data from two different external services concurrently and combines their results.

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductAggregatorService {

    private final ExecutorService ioPool = Executors.newFixedThreadPool(50);

    // Simulates calling an external service
    private String fetchProductInfo(int productId) {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        return "ProductInfo-" + productId;
    }

    // Simulates calling another external service
    private String fetchProductReviews(int productId) {
        try { Thread.sleep(150); } catch (InterruptedException e) {}
        return "Reviews-" + productId;
    }

    public CompletableFuture<String> getAggregatedProductData(int productId) {
        CompletableFuture<String> infoFuture = CompletableFuture.supplyAsync(() -> fetchProductInfo(productId), ioPool);
        CompletableFuture<String> reviewsFuture = CompletableFuture.supplyAsync(() -> fetchProductReviews(productId), ioPool);

        return infoFuture.thenCombine(reviewsFuture, (info, reviews) -> "Combined: " + info + " & " + reviews);
    }

    public static void main(String[] args) throws Exception {
        ProductAggregatorService service = new ProductAggregatorService();
        CompletableFuture<String> resultFuture = service.getAggregatedProductData(123);

        resultFuture.thenAccept(System.out::println);

        // Keep the main thread alive to see the result
        Thread.sleep(500);
        ((ExecutorService) service.ioPool).shutdown();
    }
}
```

### Deep Dive into Trade-offs: `CompletableFuture` vs. Virtual Threads (Project Loom)
*   **`CompletableFuture`:**
    *   **Pros:** Available since Java 8. Mature and well-understood. Forces you to think asynchronously.
    *   **Cons:** Can lead to "callback hell" (`thenApply`, `thenCombine`, etc.). The programming model is different from simple, synchronous code.
*   **Virtual Threads (Project Loom, Java 19+):**
    *   **Pros:** Allows you to write simple, synchronous-looking blocking code that is executed in a non-blocking way by the JVM. Much easier to read and debug.
    *   **Cons:** Still relatively new. Requires a shift in mindset for those used to the callback model.
*   **Verdict:** For new applications on Java 19+, virtual threads are often the better choice for I/O-bound tasks. For older codebases or for complex dataflow pipelines, `CompletableFuture` is still a powerful and relevant tool.

---

*This is a sample of the rewritten chapter. I would continue in this style for `ConcurrentHashMap` and other primitives.*
