# 08a - Java Streams: Declarative Data Processing

Introduced in Java 8, the Stream API provides a powerful, declarative way to process sequences of elements. Streams allow you to chain together operations to form a pipeline, making your data processing code more concise, readable, and often parallelizable.

**What's in this chapter:**
*   [The Stream Pipeline: Source, Intermediate, and Terminal Operations](#1-the-stream-pipeline)
*   [Common Stream Operations](#2-common-stream-operations)
*   [Hands-On Lab: A Stream-Powered Example](#3-hands-on-lab-a-stream-powered-example)

---

## 1. The Stream Pipeline

A stream pipeline consists of three stages:
1.  **Source:** Where the stream comes from (e.g., a `List`, an array, a file).
2.  **Intermediate Operations:** A series of operations that transform the stream. These are *lazy*—they don't execute until a terminal operation is invoked.
3.  **Terminal Operation:** An operation that triggers the processing of the stream and produces a result or a side-effect (e.g., collecting to a `List`, printing each element).

**Visualizing the Stream Pipeline:**

```mermaid
graph TD
    subgraph Stream Pipeline
        direction LR
        A(Source) --> B(Intermediate Op 1)
        B --> C(Intermediate Op 2)
        C --> D(Terminal Op)
    end

    subgraph Example
        direction LR
        L(List<String>) -- ".stream()" --> S(Stream<String>)
        S -- ".filter(s -> s.startsWith("A"))" --> F(Filtered Stream)
        F -- ".map(String::toUpperCase)" --> M(Mapped Stream)
        M -- ".collect(Collectors.toList())" --> R(Result: List<String>)
    end

    A --- L
    B --- F
    C --- M
    D --- R
```

---

## 2. Stream Operations

Here is a more comprehensive list of Stream operations.

### Intermediate Operations
These operations are lazy and return a new stream, allowing them to be chained together.

| Method         | Description                                                                                                | Example Code                                                                        |
| :------------- | :--------------------------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------------- |
| `filter()`     | Selects elements that match a given `Predicate`.                                                           | `Stream.of(1, 2, 3, 4).filter(n -> n % 2 == 0)` results in a stream of `[2, 4]`      |
| `map()`        | Transforms each element using a given `Function`.                                                          | `Stream.of("a", "b").map(String::toUpperCase)` results in a stream of `["A", "B"]`    |
| `flatMap()`    | Transforms each element into a stream of other objects and then flattens them into a single stream.        | `Stream.of(List.of(1,2), List.of(3,4)).flatMap(List::stream)` results in `[1,2,3,4]` |
| `distinct()`   | Returns a stream with unique elements (based on `equals()`).                                               | `Stream.of(1, 2, 2, 3).distinct()` results in a stream of `[1, 2, 3]`               |
| `sorted()`     | Sorts the elements based on their natural order or a `Comparator`.                                       | `Stream.of(3, 1, 2).sorted()` results in a stream of `[1, 2, 3]`                    |
| `peek()`       | Performs an action on each element as it is consumed from the stream. Mostly used for debugging.           | `Stream.of("a","b").peek(System.out::println)` prints "a", "b"                      |
| `limit()`      | Truncates the stream to be no longer than `maxSize` in length.                                             | `Stream.of(1, 2, 3).limit(2)` results in a stream of `[1, 2]`                        |
| `skip()`       | Discards the first `n` elements of the stream.                                                             | `Stream.of(1, 2, 3).skip(1)` results in a stream of `[2, 3]`                         |
| `takeWhile()` (Java 9+) | Returns a stream consisting of the longest prefix of elements taken from this stream that match the given predicate. | `Stream.of(2,4,6,7,8).takeWhile(n -> n % 2 == 0)` results in `[2,4,6]` |
| `dropWhile()` (Java 9+) | Returns a stream consisting of the remaining elements of this stream after dropping the longest prefix of elements that match the given predicate. | `Stream.of(2,4,6,7,8).dropWhile(n -> n % 2 == 0)` results in `[7,8]` |

### Terminal Operations
These operations trigger the stream pipeline processing and produce a result or a side-effect.

| Method          | Description                                                                                               | Example Code                                                                             |
| :-------------- | :-------------------------------------------------------------------------------------------------------- | :--------------------------------------------------------------------------------------- |
| `forEach()`     | Performs an action for each element.                                                                      | `Stream.of("a", "b").forEach(System.out::print)` prints "ab"                               |
| `forEachOrdered()`| Performs an action for each element, in the encounter order of the stream.                                | `Stream.of("b", "a").parallel().forEachOrdered(System.out::print)` prints "ba"            |
| `toArray()`     | Gathers the stream elements into an array.                                                                | `String[] array = Stream.of("a", "b").toArray(String[]::new)` results in `["a", "b"]`      |
| `reduce()`      | Combines the stream elements into a single value using an associative accumulation function.              | `Integer sum = Stream.of(1, 2, 3).reduce(0, (a, b) -> a + b)` results in `6`              |
| `collect()`     | Gathers the stream elements into a `Collection` (e.g., `List`, `Set`, `Map`) using a `Collector`.         | `List<Integer> list = Stream.of(1, 2).collect(Collectors.toList())` results in `[1, 2]`   |
| `min()`         | Returns the minimum element of the stream according to a `Comparator`.                                    | `Optional<Integer> min = Stream.of(3, 1, 2).min(Integer::compare)` results in `Optional[1]` |
| `max()`         | Returns the maximum element of the stream according to a `Comparator`.                                    | `Optional<Integer> max = Stream.of(3, 1, 2).max(Integer::compare)` results in `Optional[3]` |
| `count()`       | Returns the count of elements in the stream.                                                              | `long count = Stream.of(1, 2, 3).count()` results in `3`                                   |
| `anyMatch()`    | Checks if any elements match a given `Predicate`.                                                         | `boolean hasEven = Stream.of(1, 2, 3).anyMatch(n -> n % 2 == 0)` results in `true`        |
| `allMatch()`    | Checks if all elements match a given `Predicate`.                                                         | `boolean allEven = Stream.of(2, 4, 6).allMatch(n -> n % 2 == 0)` results in `true`        |
| `noneMatch()`   | Checks if no elements match a given `Predicate`.                                                          | `boolean noneNegative = Stream.of(1, 2, 3).noneMatch(n -> n < 0)` results in `true`       |
| `findFirst()`   | Returns the first element of the stream.                                                                  | `Optional<Integer> first = Stream.of(1, 2, 3).findFirst()` results in `Optional[1]`      |
| `findAny()`     | Returns any element of the stream. This is useful in parallel streams.                                    | `Optional<Integer> any = Stream.of(1, 2, 3).parallel().findAny()` could be `1`, `2`, or `3` |

---

## 3. Hands-On Lab: A Stream-Powered Example

We've created a runnable project in the `code/` directory that demonstrates:
1.  Creating a stream from a `List`.
2.  Using `filter` and `map` to process the stream.
3.  Using `collect` to gather the results into a new `List`.

**To run it:**
1.  Navigate to the `code/` directory.
2.  Run `mvn compile exec:java`.
3.  Explore the source code to see the Stream API in action.
