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

### Q4: What's the difference between `double` and `float`?

*   **Simple Answer:** They are both for decimal numbers, but `double` has about twice the precision of `float`. You should use `double` for almost everything.
*   **Detailed Explanation:**
    *   **Storage:** `float` uses 4 bytes, while `double` uses 8 bytes.
    *   **Precision:** `double` can accurately store about 15-17 decimal digits, while `float` can only handle about 6-7.
*   **Key Takeaway:** Always use `double` for calculations unless you are working with huge arrays of numbers where memory is a critical concern (e.g., graphics programming). For money, use the `BigDecimal` class to avoid rounding errors.

### Q5: What does the `final` keyword do?

*   **Simple Answer:** It makes something unchangeable.
*   **Detailed Explanation:**
    1.  **`final` variable:** A constant. Its value cannot be changed after it's assigned.
    2.  **`final` method:** Cannot be overridden by a child class.
    3.  **`final` class:** Cannot be extended or inherited from. The `String` class is a good example.

### Q6: What is autoboxing and unboxing?

*   **Simple Answer:** It's the automatic conversion between primitive types (like `int`) and their wrapper classes (like `Integer`).
*   **Detailed Explanation:**
    *   **Autoboxing:** Primitive -> Wrapper. `Integer iWrapper = 10;` (the compiler does the `new Integer(10)` part for you).
    *   **Unboxing:** Wrapper -> Primitive. `int i = iWrapper;` (the compiler does the `iWrapper.intValue()` part for you).
*   **Key Takeaway:** This was added for convenience, but be careful. Creating many wrapper objects in a tight loop can be slow and create work for the garbage collector.

### Q7: Is Java pass-by-value or pass-by-reference?

*   **Simple Answer:** Java is **always pass-by-value**.
*   **Detailed Explanation:**
    *   When you pass a primitive (like `int`) to a method, a **copy of the value** is passed. Changes inside the method don't affect the original.
    *   When you pass an object to a method, a **copy of the reference (the memory address)** is passed.
    *   This is the tricky part: both the original reference and the copy point to the *same object*. So if you use the reference copy to *change the object's internal state* (e.g., `myObject.setName("new name")`), the original object is changed.
    *   However, if you try to *reassign the reference copy* to a new object, it does not affect the original reference.

### Q8: Why is `String` immutable? What's the difference between `String`, `StringBuilder`, and `StringBuffer`?

*   **Simple Answer:** `String` is immutable (cannot be changed) for security and performance reasons. For building strings, use `StringBuilder`.
*   **Detailed Explanation:**
| Class | Mutability | Thread-Safety | When to Use |
| :--- | :--- | :--- | :--- |
| `String` | Immutable | Yes | Default choice for text that won't change. |
| `StringBuilder` | Mutable | No | Best for building strings in a single thread (e.g., in a loop). Fastest option. |
| `StringBuffer` | Mutable | Yes | Old, slow version. Use only if you need to modify a string from multiple threads. |

*   **Why immutable?**
    1.  **String Pool:** Java saves memory by reusing strings. This is only safe if they can't be changed.
    2.  **Security:** Prevents malicious code from changing a string after a security check.
    3.  **Concurrency:** Immutable objects are automatically thread-safe.

### Q9: How do you reverse an integer without converting it to a string?

*   **Simple Answer:** Use the modulo (`%`) and division (`/`) operators in a loop to pick off and place digits one by one.
*   **The Code:**
    ```java
    public int reverse(int x) {
        int reversed = 0;
        while (x != 0) {
            // Check for overflow before it happens
            if (reversed > Integer.MAX_VALUE / 10 || reversed < Integer.MIN_VALUE / 10) {
                return 0; // Overflow
            }
            reversed = reversed * 10 + x % 10; // Add the last digit of x to reversed
            x = x / 10; // Remove the last digit from x
        }
        return reversed;
    }
    ```

---

[Previous: 01 - Getting Started: Your First Conversation with the JVM](../01-Getting-Started/README.md) | [Next: 03 - Object-Oriented Programming: Building with Blueprints](../03-Object-Oriented-Programming/README.md)
