# 14 - New Java Features: The Evolution of the Language

The world of software development is constantly changing. As the current stewards of the Java language, our job is to ensure that Java continues to evolve to meet the needs of modern developers.

Since Java 9, we've moved to a new, faster release cadence: a new version of Java every six months. This allows us to get new features into the hands of developers more quickly.

Here are some of the most important features we've added in recent years.

## 1. Records: True Immutable Data Carriers

**System Design Context:** In distributed systems, you are constantly passing data between services. These Data Transfer Objects (DTOs) should be simple, immutable carriers of data. Before records, this meant writing hundreds of lines of boilerplate `getters`, `equals`, `hashCode`, and `toString`.

**The Principal's Take:** `records` are the canonical way to model immutable data aggregates. They are not "just boilerplate reduction". They are a semantic statement: "This object is a simple, transparent holder for its data."

### Complete, Runnable Example:
```java
public class RecordExample {

    // A record is a concise, final, immutable data class.
    public record UserDto(long id, String email) {
        // You can add compact constructors for validation.
        public UserDto {
            if (id < 0) {
                throw new IllegalArgumentException("ID cannot be negative");
            }
        }
    }

    public static void main(String[] args) {
        UserDto user = new UserDto(123L, "test@example.com");
        System.out.println(user); // Prints: UserDto[id=123, email=test@example.com]
        System.out.println("User ID: " + user.id()); // Accessor method
    }
}
```

### Memory Flow & JVM Deep Dive:
A `record` is just a special kind of `class` under the hood. When `new UserDto(...)` is called:
1.  An object is allocated on the **Heap**.
2.  The object header points to the `UserDto` class metadata.
3.  The fields (`id` and `email` reference) are stored in the object. They are `private` and `final` by default.
4.  The compiler auto-generates the canonical constructor, `equals()`, `hashCode()`, `toString()`, and accessor methods (e.g., `id()`, `email()`).

---

## 2. Pattern Matching: Type-Safe, Boilerplate-Free Logic

**System Design Context:** Polymorphism is great, but sometimes you have to work with a general type (`Object`, or a `sealed` interface) and perform different logic based on the concrete type. Pattern matching cleans up this code dramatically.

**The Principal's Take:** Pattern matching isn't just about saving a few lines of code. It moves type-checking logic from boilerplate `if/else` chains into the language itself, making it more declarative and less error-prone. It's a key enabler for features like `sealed` classes.

### Complete, Runnable Example:
```java
public class PatternMatchingExample {

    sealed interface Shape permits Circle, Square {}
    record Circle(double radius) implements Shape {}
    record Square(double side) implements Shape {}

    public static double getArea(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square s -> s.side() * s.side();
        };
    }

    public static void main(String[] args) {
        Shape circle = new Circle(10);
        System.out.println("Area of circle: " + getArea(circle));
    }
}
```

### Deep Dive into Trade-offs:
*   **Pattern Matching `switch` vs. Polymorphism:** For a closed set of types (like a `sealed` interface), a pattern matching `switch` is often cleaner and more maintainable than implementing a method on each subclass (the classic visitor or polymorphic pattern). It co-locates the logic. For an open set of types, polymorphism is more extensible.
*   **Guards:** You can add `when` clauses (guards) to your patterns for more complex logic, e.g., `case Circle c when c.radius() > 100 -> ...`.

---
## Interview Deep Dives

### What is the difference between an LTS and a non-LTS version of Java?

**Detailed Explanation:**
Since Java 9, Oracle has moved to a new, time-based release model with a new feature release every six months.

*   **Feature Releases (non-LTS):** These are the releases that come out every six months (e.g., Java 12, 13, 14, 15, 16). They introduce new features and are supported for a short period of time (only until the next release).
*   **Long-Term Support (LTS) Releases:** Every two years (as of Java 17), one of the feature releases is designated as an LTS release (e.g., Java 8, 11, 17, 21). These releases receive long-term support from Oracle and other vendors, including security updates and bug fixes, for many years.

**The Principal's Take:**
*   **System Design:** For any production system, you should **always use an LTS version of Java**. This ensures that you will continue to receive critical security patches and stability fixes for the lifetime of your application.
*   **Staying Current:** While you should run production on an LTS version, as a principal engineer, you are expected to keep up with the features being introduced in the non-LTS releases. This allows you to understand the direction of the language and to be prepared for the next LTS release. You can (and should) experiment with new features from the non-LTS releases in non-production environments.
*   **Interview Tip:** Being able to articulate the difference between LTS and non-LTS releases and to explain why you would choose an LTS version for production shows that you are thinking about the long-term health and security of your applications, which is a key trait of a senior and principal engineer.

---

### A Deep Dive into the Stream API

The Stream API is not just a tool for writing less code; it's a paradigm shift from imperative to declarative data processing.

*   **The Stream Pipeline:** Think of a stream pipeline as an assembly line for your data: `source -> intermediate op -> ... -> terminal op`. Streams are **lazy**; no work is done until the terminal operation is called. This allows the JVM to be very efficient.
*   **Advanced `Collectors`:** The real power of streams comes from the `Collectors` class. You can use collectors like `groupingBy` and `summingDouble` to perform complex aggregations and transformations on your data.
*   **Parallel Streams:** Be very careful with `parallel()`. It is not a magic performance booster. It uses the common `ForkJoinPool`, and if you submit a task that blocks (e.g., a network call), you can starve the pool and harm your application's performance. Only use it for CPU-intensive tasks on very large datasets.

---

[Previous: 13 - The Java Ecosystem: Tools of the Trade](../13-Java-Ecosystem/README.md) | [Next: 15 - JDBC and Database Connectivity](../15-JDBC/README.md)
