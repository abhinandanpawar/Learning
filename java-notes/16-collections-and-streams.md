# 16 - Collections & Streams Cheat-Sheet

This chapter provides a concise cheat-sheet for choosing the right collection and for building effective stream pipelines.

## Choosing a Collection

| Collection | When to Use | Key Consideration |
| --- | --- | --- |
| `ArrayList` | The default choice for a list. | Fast random access (reads). |
| `LinkedList` | Rarely. | Use when you need constant-time additions/removals at the **ends** of the list. |
| `HashMap` | The default choice for a map. | Fast key-value lookups. |
| `ConcurrentHashMap` | For multi-threaded access to a map. | Optimized for high-concurrency reads. |
| `HashSet` | When you need a collection of unique items. | Fast `contains()` checks. |

## Stream Pipeline Template

Here is a template for a typical stream pipeline.

```java
List<Result> out =
    input.stream()
         .filter(this::isValid)           // a Predicate to filter items
         .sorted(Comparator.comparing(Foo::score).reversed()) // sort by score, descending
         .map(this::toResult)             // a Function to transform items
         .limit(100)                      // short-circuiting operation
         .collect(Collectors.toList());   // terminal operation to collect results
```

> **Interview Tip:** Be prepared to explain the difference between intermediate operations (like `filter`, `map`) and terminal operations (like `collect`, `forEach`). Intermediate operations are lazy; they don't do any work until a terminal operation is invoked.

## Common Stream Pitfalls

> **Best Practice:** Avoid these common mistakes when working with streams.

*   **Stateful Lambdas:** Don't modify external state from within a lambda. This can lead to unpredictable results, especially in parallel streams.
*   **Costly `peek()`:** Don't run expensive operations in `peek()`. It's intended for debugging, not for business logic.
*   **Misusing `parallel()`:** `parallel()` is not a magic bullet for performance. It only helps when:
    *   The work per element is high (>= 1ms).
    *   The data set is large (> 10,000 items).
    *   The stream source can be easily split.

---
[< Previous: 15 - Core Language Refresh (Java 17+)](./15-core-language-refresh.md) | [Up: Table of Contents](./README.md) | [Next: 17 - Concurrency Essentials >](./17-concurrency-essentials.md)
