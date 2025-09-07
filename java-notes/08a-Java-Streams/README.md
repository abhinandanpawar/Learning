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

## 2. Common Stream Operations

### Intermediate Operations
These operations return a new stream.

| Method     | Description                                                               | Example                               |
| :--------- | :------------------------------------------------------------------------ | :------------------------------------ |
| `filter()` | Selects elements that match a given `Predicate`.                          | `s.filter(n -> n > 10)`               |
| `map()`    | Transforms each element using a given `Function`.                         | `s.map(String::length)`               |
| `sorted()` | Sorts the elements based on their natural order or a `Comparator`.      | `s.sorted()`                          |
| `distinct()`| Returns a stream with unique elements (according to `equals()`).         | `s.distinct()`                        |

### Terminal Operations
These operations produce a result or a side-effect.

| Method        | Description                                                              | Return Type      |
| :------------ | :----------------------------------------------------------------------- | :--------------- |
| `forEach()`   | Performs an action for each element.                                     | `void`           |
| `collect()`   | Gathers the stream elements into a collection (e.g., `List`, `Set`, `Map`). | `Collection`     |
| `reduce()`    | Combines the stream elements into a single value.                        | `Optional<T>`    |
| `findFirst()` | Returns the first element of the stream.                                 | `Optional<T>`    |
| `anyMatch()`  | Checks if any elements match a given `Predicate`.                        | `boolean`        |

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
