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

[Previous: 01 - Getting Started: Your First Conversation with the JVM](../01-Getting-Started/README.md) | [Next: 03 - Object-Oriented Programming: Building with Blueprints](../03-Object-Oriented-Programming/README.md)
