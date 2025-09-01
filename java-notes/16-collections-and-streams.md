# 16 - Collections & Streams: A Principal's Field Guide

Choosing the right data structure is a critical micro-optimization that has macro-level impacts on performance and memory. Similarly, writing clean, efficient streams is a hallmark of a senior developer.

---

## 1. Choosing the Right Collection: A Decision Tree

**System Design Context:** Every time you store a group of objects, you are making a system design decision. Are you optimizing for read speed, write speed, memory usage, or thread safety?

*   **Do you need key-value access?**
    *   **Yes** -> Use a `Map`.
        *   **Is it accessed by multiple threads?**
            *   **Yes** -> `ConcurrentHashMap` (your default for concurrent access).
            *   **No** -> `HashMap` (your default for single-threaded access).
        *   **Do you need to maintain insertion order?** -> `LinkedHashMap`.
        *   **Do you need to maintain sorted order?** -> `TreeMap`.
*   **No** (you have a group of items) -> Use a `Collection`.
    *   **Do you need to store duplicates?**
        *   **No** -> Use a `Set`.
            *   **Do you need sorted order?** -> `TreeSet`.
            *   **No** -> `HashSet` (your default for unique items).
        *   **Yes** -> Use a `List`.
            *   **Are you doing a lot of `get(index)` operations?** -> `ArrayList` (your default list).
            *   **Are you doing a lot of `add/remove` at the *ends* of the list?** -> `ArrayDeque` (a better choice than `LinkedList` for stack/queue operations).
            *   **`LinkedList`?** -> **Almost never.** `ArrayList` is usually faster even for insertions/deletions in the middle, due to better cache locality. Only consider `LinkedList` if you have very large lists and are doing insertions in the middle with an iterator.

---

## 2. The Anatomy of a High-Performance Stream

**The Principal's Take:** A clean stream pipeline is like a well-oiled assembly line. The order of operations matters. **Filter first, then sort, then map.** This ensures you do expensive work on the smallest possible set of data.

### Complete, Runnable Example:
This example finds the top 5 most expensive products from a list, but only considers products that are in stock.

```java
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    record Product(String name, double price, boolean inStock) {}

    public static void main(String[] args) {
        List<Product> products = List.of(
            new Product("Laptop", 1200.00, true),
            new Product("Mouse", 25.00, false),
            new Product("Keyboard", 75.00, true),
            new Product("Monitor", 300.00, true),
            new Product("Webcam", 50.00, false),
            new Product("Dock", 150.00, true),
            new Product("Chair", 250.00, true)
        );

        List<String> top5ExpensiveInStock = products.stream()
            // 1. Filter first: work with a smaller set of data
            .filter(Product::inStock)
            // 2. Sort: sort the smaller set
            .sorted(Comparator.comparing(Product::price).reversed())
            // 3. Map: only map the items you actually need
            .map(Product::name)
            // 4. Limit: take only the top 5
            .limit(5)
            // 5. Collect: put the results in a list
            .collect(Collectors.toList());

        System.out.println(top5ExpensiveInStock);
    }
}
```

### Production-Oriented Advice & Trade-offs:
*   **Parallel Streams:** Be very careful with `parallel()`. The overhead of coordinating threads can often make the stream *slower*. Only use it when you have a very large dataset and the work done per element is significant and can be done independently.
*   **`Collectors.groupingBy` Performance:** Be aware that `groupingBy` can be memory-intensive, as it has to hold all the elements for all groups in memory. For very large datasets, consider a multi-stage aggregation or a database query.
*   **Avoid Stateful Lambdas:** A stateful lambda is one that modifies a variable outside its scope. This is a major anti-pattern and will lead to unpredictable results, especially in parallel streams.

---
[< Previous: 15 - Core Language Refresh: A Principal's View on Modern Java (17+)](./15-core-language-refresh.md) | [Up: Table of Contents](./README.md) | [Next: 17 - Concurrency Essentials: A Principal Engineer's Guide >](./17-concurrency-essentials.md)
