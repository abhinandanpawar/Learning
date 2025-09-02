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

### Q: What are the primitive data types in Java? What is Autoboxing?

**The Code Example:**
```java
public class PrimitivesExample {
    public static void main(String[] args) {
        // Primitive types
        int i = 10;
        double d = 3.14;
        char c = 'A';
        boolean b = true;

        // Autoboxing: The compiler automatically converts the primitive 'int' to an 'Integer' object.
        Integer iWrapper = i;

        // Unboxing: The compiler automatically converts the 'Integer' object back to a primitive 'int'.
        int unboxedInt = iWrapper;

        System.out.println("Primitive int: " + i);
        System.out.println("Boxed Integer: " + iWrapper);
        System.out.println("Unboxed int: " + unboxedInt);
    }
}
```

**Detailed Explanation:**
Java has eight primitive data types: `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, and `char`. These are not objects; they are the most basic data types available in the language.

*   **Autoboxing** is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes (e.g., `int` to `Integer`).
*   **Unboxing** is the reverse: converting an object of a wrapper type to its corresponding primitive value.

**The Principal's Take:**
*   **Why does Java have primitive types?** This was a performance-driven design decision. We knew that for simple, frequently used data like numbers, the overhead of creating an object for each one would be too high. By having primitive types that are stored directly on the stack, we made basic arithmetic operations much faster. This is also why Java is sometimes not considered a "pure" object-oriented language.
*   **When does it matter?** While autoboxing is convenient, be aware of it in performance-critical code, especially in loops. Creating a large number of wrapper objects can put pressure on the garbage collector.

    ```java
    // This can create millions of Integer objects, which can be slow.
    Integer sum = 0;
    for (int i = 0; i < 1_000_000; i++) {
        sum += i; // Unboxing of 'sum', then boxing of the result in each iteration.
    }
    ```
    In such cases, using the primitive `int` for the sum would be much more efficient.

---

### Q: Is Java "pass-by-value" or "pass-by-reference"?

This is a classic and often misunderstood topic in Java.

**The Principal's Take:** Java is always **pass-by-value**. There is no "pass-by-reference" in Java. However, the confusion arises because Java passes a *copy of the reference* to an object.

Let's break this down with an example.

**The Code Example:**
```java
public class PassByValueExample {

    static class Product {
        String name;
        Product(String name) { this.name = name; }
    }

    public static void main(String[] args) {
        // 1. Primitives are passed by value (a copy of the value).
        int price = 100;
        modifyPrice(price);
        System.out.println("Price after method call: " + price); // Prints 100

        // 2. Objects are also passed by value (a copy of the reference).
        Product laptop = new Product("Laptop");
        modifyProduct(laptop);
        System.out.println("Product name after method call: " + laptop.name); // Prints "MacBook"
    }

    public static void modifyPrice(int p) {
        p = 200; // Modifies the copy, not the original.
    }

    public static void modifyProduct(Product prod) {
        // 'prod' is a copy of the reference. Both the original 'laptop'
        // reference and the 'prod' reference point to the same object on the heap.
        prod.name = "MacBook"; // This modifies the original object.

        // However, if we reassign the reference, it doesn't affect the original.
        // prod = new Product("Desktop");
    }
}
```

**Detailed Explanation:**

*   **Passing Primitives:** When you pass a primitive type like `int` to a method, a copy of the value is made. The method receives the copy, and any changes to it do not affect the original variable.
*   **Passing Objects:** When you pass an object to a method, you are also passing a value. But the value is a **reference** to the object on the heap. The method receives a **copy of this reference**.
    *   Since both the original reference and the copied reference point to the *same object* on the heap, if you use the copied reference to modify the object's state (e.g., `prod.name = "MacBook"`), the change is reflected in the original object.
    *   However, if you reassign the copied reference to a new object (e.g., `prod = new Product("Desktop")`), you are only changing the copy. The original reference still points to the original object.

This is the key distinction. You are passing the reference by value, not the object by reference.

---

### Q: Why are Strings immutable in Java? What's the difference between String, StringBuffer, and StringBuilder?

This is a very common and important set of questions.

**The Principal's Take:** String immutability is a core design decision in Java with significant benefits for security, performance, and concurrency.

**Detailed Explanation:**

*   **Immutability:** An immutable object is one whose state cannot be changed after it is created. When you "modify" a `String` (e.g., by concatenating another string), you are actually creating a new `String` object.
*   **Why?**
    1.  **Security:** String is widely used as a parameter for many Java classes (e.g., for network connections, opening files). If `String` were mutable, a malicious user could change the value of the string after a security check, potentially gaining unauthorized access.
    2.  **Performance (String Pool):** The JVM maintains a "String pool" in the heap. When you create a string literal (e.g., `String s = "hello";`), the JVM checks if that string already exists in the pool. If it does, it returns a reference to the existing string. If not, it creates a new one. This saves memory, and it's only possible because strings are immutable.
    3.  **Concurrency:** Because strings are immutable, they are inherently thread-safe. You can share them between multiple threads without any need for synchronization.
    4.  **`hashCode()` Caching:** The `hashCode()` of a `String` is calculated once and cached. Since the string is immutable, its hash code will never change. This makes it very fast as a key in a `HashMap`.

*   **`String` vs. `StringBuffer` vs. `StringBuilder`:**

| Feature | `String` | `StringBuffer` | `StringBuilder` |
|---|---|---|
| **Mutability** | Immutable | Mutable | Mutable |
| **Thread Safety**| Thread-safe | Thread-safe (synchronized) | Not thread-safe |
| **Performance** | Slow for frequent modifications | Slower (due to synchronization) | Fastest for modifications |

**The Principal's Take on Usage:**
*   Use **`String`** for any string value that will not change. This is the vast majority of cases.
*   Use **`StringBuilder`** when you need to build a string in a loop or through multiple modifications in a single-threaded context. It's the most efficient way to do this.
*   Use **`StringBuffer`** only in the rare case that you need to share a mutable string between multiple threads. In most cases, you should use other concurrency mechanisms.

**Bonus: Why use `char[]` for passwords?**
Because `String` objects are immutable and go into the String pool, a password stored as a `String` can remain in memory for a long time, even after you are done with it. This is a security risk, as a hacker with access to a memory dump could find the password in plain text. A `char[]`, on the other hand, is a mutable array. You can (and should) explicitly wipe the array (e.g., by filling it with zeros) after you have used it, minimizing the time the password is in memory.

---

### Q: How would you solve common numeric problems like reversing an integer or checking for a palindrome?

These are classic interview questions that test your ability to manipulate numbers without converting them to strings.

**The Principal's Take:** The key to these problems is to use the modulo (`%`) and division (`/`) operators to work with digits individually.

**1. Reverse Integer**
The goal is to reverse the digits of an integer (e.g., 123 -> 321).

```java
public int reverse(int x) {
    int rev = 0;
    while (x != 0) {
        // This check is important to prevent overflow
        if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
            return 0; // Or throw an exception
        }
        rev = rev * 10 + x % 10;
        x = x / 10;
    }
    return rev;
}
```

**2. Palindrome Number**
The goal is to check if an integer is a palindrome (e.g., 121 is, 123 is not). This can be done by reversing the number and checking if it's equal to the original.

```java
public boolean isPalindrome(int x) {
    // Negative numbers are not palindromes
    if (x < 0) return false;

    int original = x;
    int reversed = 0;
    while (x != 0) {
        reversed = reversed * 10 + x % 10;
        x = x / 10;
    }
    return original == reversed;
}
```

**3. Pow(x, n)**
The goal is to calculate `x` to the power of `n`. The naive approach of multiplying `x` `n` times is too slow (O(n)). A better approach is to use recursion (O(log n)).

```java
public double myPow(double x, int n) {
    if (n == 0) return 1;
    if (n < 0) {
        x = 1 / x;
        // Handle the edge case of Integer.MIN_VALUE
        if (n == Integer.MIN_VALUE) {
            return x * myPow(x, Integer.MAX_VALUE);
        }
        n = -n;
    }
    return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
}
```
This recursive solution is much more efficient as it halves the problem in each step.

---

[Previous: 01 - Getting Started: Your First Conversation with the JVM](../01-Getting-Started/README.md) | [Next: 03 - Object-Oriented Programming: Building with Blueprints](../03-Object-Oriented-Programming/README.md)
