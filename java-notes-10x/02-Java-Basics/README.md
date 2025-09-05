# 02 - Java Basics: The Building Blocks of the Language

Welcome back. Now that you've set up your environment, it's time to learn the basic vocabulary of the Java language. As we go through these concepts, we'll continue our peek behind the curtain to see how the JVM handles things.

**What's in this chapter:**
*   [Variables: Primitives vs. References](#1-variables-the-nouns-of-our-language)
*   [Operators: The Verbs of Our Language](#2-operators-the-verbs-of-our-language)
*   [Control Flow: The Grammar of Our Language](#3-control-flow-the-grammar-of-our-language)
*   [Hands-On Lab: A Simple Calculator](#4-hands-on-lab-a-simple-calculator)
*   [Interview Deep Dives](#interview-deep-dives)

---

## 1. Variables: The Nouns of Our Language

A variable is a named piece of memory that holds a value. When we designed Java, we decided to have two kinds of variables: **primitive types** and **reference types**. This distinction is crucial for understanding Java's performance and memory management.

### a. Primitive Types: The Simple Stuff

Primitives are the most basic data types. They are not objects. They hold a single, simple value directly in their allocated memory.

*   `int`, `long`, `short`, `byte`: For whole numbers.
*   `double`, `float`: For floating-point (decimal) numbers.
*   `char`: For a single character (e.g., `'A'`).
*   `boolean`: For `true` or `false` values.

### b. Reference Types: Pointers to Objects

A reference type variable does not hold the object itself, but rather a *reference* (a memory address) to an object. The object itself lives on the **heap**. The most common reference type you'll start with is `String`.

### JVM Deep Dive: Stack vs. Heap

The JVM organizes memory into two main areas: the **stack** and the **heap**.
*   **The Stack:** Fast, but small. Each thread gets its own stack. It's used for storing local variables (primitives and object references) and managing method calls. Memory is automatically reclaimed when a method finishes.
*   **The Heap:** Slower, but large. Shared by all threads. This is where all objects are created (`new`). Memory is managed by the **Garbage Collector (GC)**.

Let's visualize it:
```java
public void myMethod() {
    int price = 100; // Primitive
    String productName = "Laptop"; // Reference
}
```

```mermaid
graph TD
    subgraph Memory
        subgraph Stack
            direction LR
            M[myMethod() Stack Frame]
            subgraph M
                P[int price: 100]
                R[String productName (reference)]
            end
        end
        subgraph Heap
            O[String Object "Laptop"]
        end
    end
    R -- points to --> O

    style Stack fill:#bbf,stroke:#333
    style Heap fill:#f9f,stroke:#333
```
The `price` variable's value (100) lives directly on the stack. The `productName` variable also lives on the stack, but its value is just a memory address pointing to the actual `String` object on the heap.

---

## 2. Operators: The Verbs of Our Language

Operators are how we perform actions on variables.
*   **Arithmetic:** `+`, `-`, `*`, `/` (division), `%` (modulo/remainder)
*   **Assignment:** `=`, `+=`, `-=`
*   **Relational:** `==` (equal to), `!=` (not equal to), `>`, `<`
*   **Logical:** `&&` (and), `||` (or), `!` (not)
*   **Increment/Decrement:** `++`, `--`

---

## 3. Control Flow: The Grammar of Our Language

Control flow statements allow us to make decisions and repeat actions.

#### `if-else` statement (Conditional Branching)
```mermaid
graph TD
    A{Is stock > 0?} -->|Yes| B[Print "In stock"]
    A -->|No| C[Print "Out of stock"]
    B --> D((End))
    C --> D
```
```java
int stock = 10;
if (stock > 0) {
    System.out.println("In stock");
} else {
    System.out.println("Out of stock");
}
```

#### `for` loop (Fixed-Iteration Looping)
Use a `for` loop when you know how many times you want to repeat an action.
```java
// Print numbers from 1 to 5
for (int i = 1; i <= 5; i++) {
    System.out.println("Current number: " + i);
}
```

#### `while` loop (Condition-Based Looping)
Use a `while` loop when you want to repeat an action as long as a condition is true.
```java
int count = 0;
while (count < 3) {
    System.out.println("Hello!");
    count++; // Don't forget to change the condition variable!
}
```

#### `switch` statement (Multi-Way Branching)
The `switch` statement is a clean way to handle multiple options for a single value. Modern Java (14+) introduced a more powerful and less error-prone **switch expression**.

**The Old Way (pre-Java 14):**
```java
// Prone to errors if you forget 'break'!
switch (dayOfWeek) {
    case 1: dayName = "Monday"; break;
    // ...
}
```

**The Modern Way (Java 14+ Switch Expression):**
This is the preferred approach. It's more concise, safer, and can return a value.
```java
int dayOfWeek = 3;
String dayName = switch (dayOfWeek) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    case 4 -> "Thursday";
    case 5 -> "Friday";
    case 6, 7 -> "Weekend";
    default -> "Unknown";
};
System.out.println(dayName); // Wednesday
```

---

## 4. Hands-On Lab: A Simple Calculator

We've created a small, runnable project in the `code/` subdirectory that uses all the concepts from this chapter. It's a simple calculator that takes two numbers and an operator.

**To run it:**
1.  Navigate to the `code/` directory.
2.  Run the project using Maven: `mvn compile exec:java`
3.  Explore the source code in `src/main/java/` to see variables, operators, and control flow in action.

---

## Interview Deep Dives

(Content from the original `README.md` for Q4-Q9, with minor formatting improvements and a more detailed explanation for Q7)

### Q7: Is Java pass-by-value or pass-by-reference?

*   **Simple Answer:** Java is **strictly and always pass-by-value**.
*   **Detailed Explanation:** This is one of the most misunderstood concepts in Java.
    *   When you pass a **primitive type** (like `int`) to a method, a **copy of the value** is passed. Changes to that copy inside the method have no effect on the original variable.
    *   When you pass an **object reference** (like a `StringBuilder`) to a method, a **copy of the reference value (the memory address)** is passed.

    Let's be very precise.
    ```java
    public void messWithVariables() {
        int original_x = 10;
        StringBuilder original_sb = new StringBuilder("Hello");

        modify(original_x, original_sb);

        // What are the values now?
        // original_x is STILL 10.
        // original_sb now contains "Hello World".
    }

    public void modify(int x_copy, StringBuilder sb_copy) {
        // 1. Modifying the primitive copy
        x_copy = 20; // This only changes the copy. The original is untouched.

        // 2. Using the reference copy to change the ORIGINAL object
        sb_copy.append(" World"); // This follows the reference and modifies the object on the heap.

        // 3. Reassigning the reference copy
        sb_copy = new StringBuilder("Goodbye"); // This now points the *copy* to a new object. The original reference is untouched.
    }
    ```
*   **The Key Takeaway:** You can't change which object an original reference points to, but you *can* change the internal state of the object it points to.

... (Other questions Q4, Q5, Q6, Q8, Q9 would be included here) ...
