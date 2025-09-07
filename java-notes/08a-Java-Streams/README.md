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

## 3. More Ways to Create Streams

Besides getting a stream from a `Collection`, you can create streams in several other ways.

| Method                       | Description                                                                    | Example Code                                                               |
| :--------------------------- | :----------------------------------------------------------------------------- | :------------------------------------------------------------------------- |
| `Stream.of(T... values)`     | Creates a stream from a sequence of objects.                                   | `Stream<String> stream = Stream.of("a", "b", "c");`                         |
| `Arrays.stream(T[] array)`   | Creates a stream from an array.                                                | `String[] array = {"a", "b"}; Arrays.stream(array);`                       |
| `Stream.iterate(T seed, UnaryOperator<T> f)` | Creates an infinite ordered stream produced by iterative application of a function. | `Stream.iterate(0, n -> n + 2).limit(5)` produces `[0, 2, 4, 6, 8]`        |
| `Stream.generate(Supplier<T> s)` | Creates an infinite unordered stream where each element is generated by a `Supplier`. | `Stream.generate(Math::random).limit(5)` produces 5 random numbers         |
| `Files.lines(Path path)`     | Creates a stream from the lines of a file.                                     | `try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) { ... }`   |

## 4. A Deeper Look at Collectors

The `collect()` method is one of the most powerful terminal operations. It uses a `Collector` to accumulate stream elements into a final result.

| Collector Method              | Description                                                                    | Example Code                                                                                             |
| :---------------------------- | :----------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------- |
| `toList()` / `toSet()`        | Collects elements into a `List` or `Set`.                                      | `List<String> list = stream.collect(Collectors.toList());`                                                |
| `toMap(keyMapper, valueMapper)` | Collects elements into a `Map`.                                                | `Map<String, Integer> map = stream.collect(Collectors.toMap(s -> s, String::length));`                   |
| `joining(delimiter)`          | Joins `String` elements into a single string.                                  | `String joined = stream.collect(Collectors.joining(", "));`                                               |
| `groupingBy(classifier)`      | Groups elements into a `Map` based on a classification function.               | `Map<Integer, List<String>> byLength = stream.collect(Collectors.groupingBy(String::length));`           |
| `partitioningBy(predicate)`   | Partitions elements into a `Map<Boolean, List<T>>` based on a predicate.       | `Map<Boolean, List<String>> passFail = stream.collect(Collectors.partitioningBy(s -> s.length() > 3));` |
| `summarizingInt(mapper)`      | Returns an `IntSummaryStatistics` object with count, sum, min, average, and max. | `IntSummaryStatistics stats = stream.collect(Collectors.summarizingInt(String::length));`                 |

## 5. Primitive Type Streams

Java provides specialized stream types for primitive `int`, `long`, and `double` to avoid the overhead of boxing and unboxing.

-   **`IntStream`**: Stream of `int` values.
-   **`LongStream`**: Stream of `long` values.
-   **`DoubleStream`**: Stream of `double` values.

These streams offer specialized methods, such as `sum()`, `average()`, `range()`, and `summaryStatistics()`.

| Method                  | Description                                                               | Example Code                                                       |
| :---------------------- | :------------------------------------------------------------------------ | :----------------------------------------------------------------- |
| `range(start, end)`     | Creates a stream of `int` or `long` from a starting to an ending value (exclusive). | `IntStream.range(1, 5)` produces `[1, 2, 3, 4]`                    |
| `sum()`                 | Calculates the sum of the elements.                                       | `int sum = IntStream.of(1, 2, 3).sum();` results in `6`              |
| `average()`             | Calculates the average of the elements.                                   | `OptionalDouble avg = IntStream.of(1, 2, 3).average();`            |
| `summaryStatistics()`   | Returns a statistics object with count, sum, min, average, and max.       | `IntSummaryStatistics stats = IntStream.of(1,2,3).summaryStatistics();` |
| `mapToObj(mapper)`      | Converts the primitive stream back to an object stream.                   | `Stream<String> stream = IntStream.range(1,3).mapToObj(i -> "v"+i);` |

## 6. Parallel Streams

A parallel stream is a stream that is processed by multiple threads. You can create a parallel stream by calling the `parallel()` method on an existing stream.

```java
List<String> list = ...;
Stream<String> parallelStream = list.parallelStream();
// or
Stream<String> parallelStream = list.stream().parallel();
```

**When to use Parallel Streams:**
-   When you have a large dataset to process.
-   When the operations on the stream elements are independent and can be performed in parallel.
-   When the cost of processing each element is high.

**Potential Pitfalls:**
-   **Overhead:** There is an overhead to setting up and managing parallel execution. For small datasets, a sequential stream is often faster.
-   **Stateful Lambdas:** Avoid using stateful lambda expressions in parallel streams, as they can lead to incorrect results due to race conditions.
-   **Ordering:** The order of elements is not guaranteed in parallel streams unless you use an ordered terminal operation like `forEachOrdered()`.


## 7. A Guide to `java.util.Optional`

The `Optional` class is a container object which may or may not contain a non-null value. It's used to avoid `NullPointerException` and to design more expressive APIs. Many stream terminal operations, like `findFirst()` or `reduce()`, return an `Optional`.

| Method              | Description                                                                    |
| :------------------ | :----------------------------------------------------------------------------- |
| `isPresent()`       | Returns `true` if there is a value present, otherwise `false`.                 |
| `get()`             | If a value is present, returns the value, otherwise throws `NoSuchElementException`. |
| `ifPresent(consumer)`| If a value is present, invokes the specified consumer with the value.        |
| `orElse(other)`     | Returns the value if present, otherwise returns `other`.                       |
| `orElseGet(supplier)`| Returns the value if present, otherwise returns the result of invoking `supplier`. |
| `orElseThrow()`     | Returns the contained value, if present, otherwise throws `NoSuchElementException`. |

## 8. Handling Exceptions in Streams

Lambda expressions in streams can't throw checked exceptions directly. Here are a few strategies to handle them:

1.  **Use a `try-catch` block inside the lambda:** This can be verbose but is straightforward.
    ```java
    stream.map(item -> {
        try {
            return methodThatThrowsCheckedException(item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    ```
2.  **Create a wrapper method:** Extract the `try-catch` logic into a helper method that returns a `Function`.
3.  **Use a library:** Libraries like Vavr or throwing-function provide functional interfaces that can handle checked exceptions.

## 9. Stream Performance and Best Practices

-   **Laziness is Key:** Intermediate operations are not executed until a terminal operation is invoked. This allows the stream pipeline to be optimized.
-   **Avoid Stateful Lambdas:** Lambdas that modify external state can lead to unpredictable results, especially in parallel streams.
-   **Prefer Primitive Streams:** Use `IntStream`, `LongStream`, and `DoubleStream` when working with primitives to avoid boxing/unboxing overhead.
-   **Be Mindful of Operation Order:** The order of intermediate operations matters. For example, `filter()` before `map()` is usually more efficient as it reduces the number of elements to be mapped.
-   **Parallelism is Not Always Faster:** Measure performance before and after applying parallelism. The overhead of managing threads can make parallel streams slower for small datasets or simple operations.

## 10. Streams vs. Traditional Loops

| Aspect        | Streams                                       | Traditional Loops (`for`, `while`)              |
| :------------ | :-------------------------------------------- | :---------------------------------------------- |
| **Readability** | Declarative, expresses *what* to do.          | Imperative, specifies *how* to do it.           |
| **Conciseness** | Generally more concise for complex operations. | Can be more verbose.                            |
| **Flexibility** | Easily allows for parallel execution.         | Parallelization requires manual thread management. |
| **Mutability**  | Encourages immutability and functional style. | Often relies on mutating state.                 |
| **Debugging**   | Can be harder to debug due to lazy evaluation. | Easier to debug with breakpoints.               |

---

## 11. Writing a Custom Collector

While the `Collectors` class provides many useful implementations, you can also create your own `Collector` by implementing the `java.util.stream.Collector` interface. This requires providing five functions:

1.  **`supplier()`**: Creates a new mutable result container.
2.  **`accumulator()`**: Adds a new element to an existing result container.
3.  **`combiner()`**: Merges two result containers into one. This is used in parallel streams.
4.  **`finisher()`**: Performs a final transformation from the intermediate result container to the final result type.
5.  **`characteristics()`**: A set of `Collector.Characteristics` that describe the collector, such as `CONCURRENT`, `UNORDERED`, and `IDENTITY_FINISH`.

Creating a custom collector is an advanced use case, but it provides ultimate flexibility when the standard collectors are not sufficient.

---

## 12. Hands-On Lab: A Stream-Powered Example


We've created a runnable project in the `code/` directory that demonstrates many of the concepts discussed in this chapter, including:
1.  Creating streams in various ways.
2.  Using a variety of intermediate and terminal operations.
3.  Working with `Collectors`.
4.  Using primitive and parallel streams.

**To run it:**
1.  Navigate to the `code/` directory.
2.  Run `mvn compile exec:java`. By default, this will run the `AdvancedStreamExample.java` file.
3.  Explore both `StreamExample.java` and `AdvancedStreamExample.java` to see the Stream API in action. You can change the `mainClass` in the `pom.xml` to run the other example.
