# 02 - Java Basics: The Building Blocks of the Language

Welcome back. Now that you've had your first conversation with the JVM, it's time to learn the basic vocabulary of the Java language. As we go through these concepts, I'll give you a peek behind the curtain to see how the JVM handles things.

## Our Running Example: A Simple E-commerce App

To make things more concrete, we're going to build a simple e-commerce application throughout these notes. We'll start with the very basics and add more features as we learn new concepts.

## 1. Variables: The Nouns of Our Language

A variable is a named piece of memory that can hold a value. When we designed Java, we decided to have two kinds of variables: primitives and references. This decision has a profound impact on how Java manages memory.

### a. Primitive Types: The Simple Stuff

Primitive types are the most basic data types. They are not objects, which makes them very fast and memory-efficient.

*   `int`, `long`, `short`, `byte`: For whole numbers.
*   `double`, `float`: For floating-point numbers.
*   `char`: For a single character.
*   `boolean`: For `true` or `false`.

**JVM Deep Dive: The Stack**

When you declare a primitive variable inside a method, the JVM allocates memory for it on the **stack**. The stack is a private area of memory for each thread of execution. It's very fast to access, but it's also limited in size.

```java
public void myMethod() {
    int price = 100; // 'price' is stored on the stack
}
```

When `myMethod` is called, a new "stack frame" is created on the stack. This frame holds the `price` variable. When the method finishes, the stack frame is popped off, and the memory is automatically reclaimed.

### b. Reference Types: Pointers to Objects

Reference types are variables that hold a reference (or a pointer) to an object. The object itself lives on the **heap**, a large, shared area of memory.

The most common reference type you'll use is `String`.

```java
public void myMethod() {
    String productName = "Laptop";
}
```

**JVM Deep Dive: The Heap and Garbage Collection**

In this example, the `productName` variable itself lives on the stack, but it holds a reference to a `String` object with the value "Laptop" that lives on the heap.

The heap is where all objects are created. Unlike the stack, the heap is shared among all threads. We designed it this way because objects can be large and can live for a long time.

But what happens when an object is no longer needed? This is where our automatic memory management, the **Garbage Collector (GC)**, comes in. The GC periodically scans the heap for objects that are no longer referenced and reclaims their memory. This was a major design decision to prevent common memory leak errors that plagued languages like C++.

## 2. Operators: The Verbs of Our Language

Operators are how we perform actions on our variables.

*   **Arithmetic:** `+`, `-`, `*`, `/`, `%`
*   **Relational:** `==`, `!=`, `>`, `<`
*   **Logical:** `&&`, `||`, `!`

## 3. Control Flow: The Grammar of Our Language

Control flow statements allow us to make decisions and repeat actions.

**`if-else` statement:**
```java
int stock = 10;

if (stock > 0) {
    System.out.println("In stock");
} else {
    System.out.println("Out of stock");
}
```

**`for` loop:**
```java
// Print numbers from 1 to 5
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

**`while` loop:**
```java
int count = 0;
while (count < 3) {
    System.out.println("Hello!");
    count++;
}
```

**`switch` statement:**
```java
int dayOfWeek = 3;
String dayName;

switch (dayOfWeek) {
    case 1:
        dayName = "Monday";
        break;
    case 2:
        dayName = "Tuesday";
        break;
    case 3:
        dayName = "Wednesday";
        break;
    // ... and so on
    default:
        dayName = "Unknown";
        break;
}
System.out.println(dayName); // Wednesday
```

In the next chapter, we'll explore the core of Java's design: Object-Oriented Programming. We'll see how we can use classes and objects to model our e-commerce application.

---

## Interview Deep Dives

### Q9: What is the difference between `double` and `float` variables in Java?

To understand this, let's check this example that deals with the price of a product.

**The Code Example:**
```java
public class PrecisionExample {
    public static void main(String[] args) {
        // float is a single-precision 32-bit floating point number
        float itemPrice = 25.99f; // Note the 'f' suffix

        // double is a double-precision 64-bit floating point number
        double orderTotal = 129.95;

        System.out.println("Item price (float): " + itemPrice);
        System.out.println("Order total (double): " + orderTotal);
    }
}
```

**Detailed Explanation:**
The primary difference between `double` and `float` is their **precision** and **storage size**.

*   **Storage:** A `float` uses 4 bytes (32 bits) of memory, while a `double` uses 8 bytes (64 bits).
*   **Precision:** This difference in storage size means a `double` can represent a much larger range of numbers with a higher degree of accuracy (about 15-17 decimal digits) compared to a `float` (about 6-7 decimal digits).

**The Principal's Take:**
*   **System Design:** For most applications, especially financial calculations, you should **always default to `double`**. In fact, for handling money, you should use the `BigDecimal` class to get exact control over rounding and precision, as floating-point numbers can introduce small inaccuracies.
*   **Memory:** The only time to consider `float` is in memory-constrained environments where you are storing a very large array of floating-point numbers, such as in scientific computing or graphics programming, and the loss of precision is acceptable.

---

### Q10: What is the `final` keyword in Java?

To understand this, let's check this example of setting a sales tax rate.

**The Code Example:**
```java
public class FinalExample {

    // 1. A final variable is a constant.
    public static final double SALES_TAX_RATE = 0.08;

    // 2. A final method cannot be overridden by subclasses.
    public final void displayWelcomeMessage() {
        System.out.println("Welcome to our store!");
    }

    // 3. A final class cannot be extended (inherited from).
    // public final class MyFinalClass {}
}
```

**Detailed Explanation:**
The `final` keyword is a modifier that can be applied to variables, methods, and classes. It has a slightly different meaning in each context, but the core idea is the same: **it makes something unchangeable.**

1.  **`final` variables:** A `final` variable is a **constant**. Its value cannot be changed after it has been initialized. This is useful for defining things like mathematical constants (`PI`) or application-wide configuration values (like our `SALES_TAX_RATE`).
    *   **Memory/JVM Insight:** `public static final` variables are true constants that are resolved at compile time. The JVM may perform optimizations like inlining their values directly into the code that uses them.

2.  **`final` methods:** A `final` method **cannot be overridden** by a subclass.
    *   **System Design:** You would use this when you have a method in a superclass that has an implementation that should not be changed by any subclass. This is a way to enforce a part of an algorithm or a contract.

3.  **`final` classes:** A `final` class **cannot be subclassed** (inherited from).
    *   **System Design:** This is used for classes that are "perfect" and should not be tampered with. A great example is the `String` class in Java. We made it `final` to ensure that its behavior is always consistent and predictable, which is crucial for security and reliability. When you pass a `String` to a method, you can be sure that the method can't change the string's content by casting it to a malicious subclass.

---

[Previous: 01 - Getting Started: Your First Conversation with the JVM](../01-Getting-Started/README.md) | [Next: 03 - Object-Oriented Programming: Building with Blueprints](../03-Object-Oriented-Programming/README.md)
