# 23 - A Deep Dive into the Stream API

The Stream API is not just a tool for writing less code; it's a paradigm shift from imperative to declarative data processing. A principal engineer must understand not only how to use streams, but also their performance characteristics and how to use them to write clean, parallelizable code.

---

## 1. The Stream Pipeline: A Mental Model

Think of a stream pipeline as an assembly line for your data. Each operation is a station on the line.

`source -> intermediate op -> intermediate op -> ... -> terminal op`

**JVM Deep Dive:** Streams are **lazy**. No work is done until the terminal operation is called. When it is, the JVM pulls each item from the source and pushes it through the entire pipeline one by one. For example, in a `filter().map()` pipeline, the first element is filtered, then mapped; then the second element is filtered, then mapped, and so on. This is more efficient than iterating over the collection multiple times.

---

## 2. Advanced `Collectors`: The Real Power of Streams

Simple `toList()` is just the beginning. The real power of streams comes from the `Collectors` class.

**System Design Context:** A common task in data processing and microservices is to aggregate and transform data. For example, you might need to take a flat list of `Order` objects and group them by customer, while also calculating the total value of orders for each customer.

### Complete, Runnable Example:
This example shows how to use `groupingBy` with a `downstream collector` (`summingDouble`) to perform a complex aggregation.

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdvancedStreamExample {

    record Order(String customer, String product, double price) {}

    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order("Alice", "Laptop", 1200.00),
            new Order("Alice", "Mouse", 25.00),
            new Order("Bob", "Monitor", 300.00),
            new Order("Alice", "Keyboard", 75.00),
            new Order("Bob", "Webcam", 50.00)
        );

        // Calculate the total value of orders for each customer
        Map<String, Double> totalByCustomer = orders.stream()
            .collect(Collectors.groupingBy(
                Order::customer,
                Collectors.summingDouble(Order::price)
            ));

        System.out.println(totalByCustomer); // {Bob=350.0, Alice=1300.0}
    }
}
```

### Production-Oriented Advice & Trade-offs:
*   **`toMap` key collisions:** When using `Collectors.toMap`, if there's a possibility of duplicate keys, you *must* provide a third argument, a `mergeFunction`, to handle the collision. Otherwise, your stream will throw an `IllegalStateException`.
*   **`groupingBy` vs. `partitioningBy`:** `groupingBy` groups elements based on a classifier function. `partitioningBy` is a special case that groups elements into a `Map<Boolean, List<T>>` based on a `Predicate`. It's often more efficient if you just need to split a collection into two groups.

---

## 3. Parallel Streams: The Double-Edged Sword

**The Principal's Take:** The single most misunderstood part of the Stream API is `parallel()`. It is not a magic performance booster. In fact, it can often make your code *slower*.

**JVM Deep Dive:** Parallel streams use the common `ForkJoinPool` by default. This pool has a limited number of threads (typically equal to the number of CPU cores). If you submit a task that blocks (e.g., a network call), you are starving the pool and preventing other tasks (including potentially unrelated parts of your application) from running.

### When to Use Parallel Streams: A Checklist
Only use a parallel stream if **all** of these are true:
1.  [ ] You have a very large dataset (e.g., millions of elements).
2.  [ ] The processing for each element is CPU-intensive and takes a significant amount of time.
3.  [ ] The work for each element is independent of the others.
4.  [ ] The stream source can be easily split (e.g., `ArrayList`, not `LinkedList`).

If you have blocking I/O, use `CompletableFuture` with a dedicated I/O executor instead.

---
[< Previous: 22 - Interview & Growth Prep](./22-interview-and-growth-prep.md) | [Up: Table of Contents](./README.md)
