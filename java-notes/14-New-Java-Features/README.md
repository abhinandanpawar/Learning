# 14 - New Java Features

Java has been evolving rapidly in recent years, with a new release every six months. This section highlights some of the most important features introduced in recent versions.

## 1. Lambda Expressions (Java 8)

Lambda expressions provide a clear and concise way to represent a method interface using an expression. They are often used to implement functional interfaces (interfaces with a single abstract method).

```java
// Before Java 8
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Hello, World!");
    }
};

// With Lambda Expression
Runnable r2 = () -> System.out.println("Hello, World!");
```

## 2. Streams API (Java 8)

The Streams API is used to process collections of objects in a functional style. A stream is a sequence of elements that supports various methods which can be pipelined to produce the desired result.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

long count = names.stream()
                  .filter(name -> name.startsWith("A"))
                  .count(); // 1
```

## 3. `var` Keyword (Java 10)

The `var` keyword allows you to declare a local variable without specifying its type. The compiler infers the type from the context.

```java
// Before Java 10
String message = "Hello, Java 10!";

// With var
var message2 = "Hello, Java 10!";
```

## 4. Records (Java 14)

Records provide a compact syntax for declaring classes that are transparent holders for immutable data.

```java
// Before Java 14
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getters, equals, hashCode, toString...
}

// With Records
public record Point(int x, int y) { }
```

## 5. Pattern Matching for `instanceof` (Java 14)

Pattern matching for `instanceof` simplifies the process of checking if an object is an instance of a specific class and then casting it.

```java
// Before Java 14
if (obj instanceof String) {
    String s = (String) obj;
    // use s
}

// With Pattern Matching
if (obj instanceof String s) {
    // use s
}
```

---

[Previous: 13 - Java Ecosystem](../13-Java-Ecosystem/README.md)
