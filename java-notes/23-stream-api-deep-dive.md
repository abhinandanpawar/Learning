# 23 - A Deep Dive into the Stream API

The Stream API, introduced in Java 8, is one of the most significant additions to the Java language. It provides a powerful and declarative way to process collections of data.

> **Best Practice:** Use streams to write more readable and expressive code for data manipulation. Instead of writing loops to describe *how* to do something, you describe *what* you want to do.

## 1. Creating Streams

You can create a stream from a variety of sources.

**From a Collection:**
```java
List<String> names = List.of("Alice", "Bob", "Charlie");
Stream<String> stream = names.stream();
```

**From an Array:**
```java
String[] namesArray = {"Alice", "Bob", "Charlie"};
Stream<String> stream = Arrays.stream(namesArray);
```

**From a range of numbers:**
```java
IntStream intStream = IntStream.range(1, 5); // 1, 2, 3, 4
LongStream longStream = LongStream.of(1L, 2L, 3L);
```

## 2. Intermediate Operations

Intermediate operations transform a stream into another stream. They are always **lazy**, meaning they don't get executed until a terminal operation is invoked.

*   **`filter(Predicate)`:** Filters elements based on a condition.
    ```java
    names.stream()
         .filter(name -> name.startsWith("A")); // "Alice"
    ```
*   **`map(Function)`:** Transforms each element.
    ```java
    names.stream()
         .map(String::toUpperCase); // "ALICE", "BOB", "CHARLIE"
    ```
*   **`sorted()`:** Sorts the elements.
    ```java
    names.stream()
         .sorted(); // "Alice", "Bob", "Charlie"
    ```
*   **`distinct()`:** Removes duplicate elements.
*   **`peek(Consumer)`:** Performs an action on each element as it flows through the stream. Mostly used for debugging.

## 3. Terminal Operations

Terminal operations produce a result or a side-effect. They trigger the execution of the stream pipeline.

*   **`forEach(Consumer)`:** Performs an action for each element.
*   **`collect(Collector)`:** Collects the elements into a `Collection` or another data structure. This is one of the most powerful terminal operations.
*   **`reduce(identity, BinaryOperator)`:** Combines the elements of a stream into a single value.
    ```java
    int sum = IntStream.range(1, 5).reduce(0, (a, b) -> a + b); // 10
    ```
*   **`count()`:** Returns the number of elements in the stream.
*   **`anyMatch(Predicate)`, `allMatch(Predicate)`, `noneMatch(Predicate)`:** Check if elements match a given condition.
*   **`findFirst()`, `findAny()`:** Return an `Optional` describing the first element of the stream.

## 4. The `Collectors` Class

The `Collectors` class provides a set of powerful terminal operations for collecting stream elements.

*   **`toList()`, `toSet()`:**
    ```java
    List<String> upperCaseNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
    ```
*   **`toMap(keyMapper, valueMapper)`:**
    ```java
    Map<String, Integer> nameLengths = names.stream().collect(Collectors.toMap(name -> name, String::length));
    ```
*   **`groupingBy(classifier)`:** Groups elements into a `Map`.
    ```java
    Map<Integer, List<String>> namesByLength = names.stream().collect(Collectors.groupingBy(String::length));
    ```
*   **`joining(delimiter)`:** Joins elements into a single `String`.
    ```java
    String joinedNames = names.stream().collect(Collectors.joining(", ")); // "Alice, Bob, Charlie"
    ```

---
[< Previous: 22 - Interview & Growth Prep](./22-interview-and-growth-prep.md) | [Up: Table of Contents](./README.md)
