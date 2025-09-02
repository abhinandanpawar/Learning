# 06 - Exception Handling: Dealing with the Unexpected

In a perfect world, programs would always run without errors. But we don't live in a perfect world. As language designers, we had to provide a robust way to handle errors. This is where exception handling comes in.

## 1. The Problem with Old-School Error Handling

In older languages like C, error handling was often done by returning error codes from functions. This was clumsy and error-prone. It was too easy for developers to forget to check the error code, leading to bugs.

We wanted a better way. We designed Java's exception handling mechanism to be:
*   **Clear:** It separates the error-handling code from the main logic.
*   **Robust:** It forces the developer to deal with certain types of errors.

## 2. `try`, `catch`, and `finally`: The Core of Our Design

*   **`try`:** You put the code that might throw an exception in the `try` block.
*   **`catch`:** You use the `catch` block to handle the exception if it occurs.
*   **`finally`:** The `finally` block is our way of guaranteeing that certain code will always run, whether an exception occurred or not. This is crucial for releasing resources like file handles or network connections.

## 3. Checked vs. Unchecked Exceptions: A Controversial Decision

This was one of our most debated design decisions. We decided to split exceptions into two categories:

*   **Checked Exceptions:** These are for exceptional conditions that a well-written application should anticipate and recover from. For example, if you're reading from a file, the file might not exist (`FileNotFoundException`). We force you to handle these exceptions at compile-time (either with a `try-catch` block or by declaring that your method `throws` it). We did this to make programs more robust.

*   **Unchecked Exceptions (Runtime Exceptions):** These are for problems that are the result of a programming error, such as `NullPointerException` or `ArrayIndexOutOfBoundsException`. We didn't want to force you to handle these everywhere, as it would clutter the code.

**JVM Deep Dive: How Exceptions Work**

When an exception is thrown, the JVM creates an exception object that contains information about the error. The JVM then unwinds the stack, looking for a `catch` block that can handle that type of exception. If it doesn't find one, the thread terminates.

## 4. Our E-commerce App: Handling a Failed Payment

Let's say we're processing a payment in our e-commerce app. The payment gateway might throw a `PaymentFailedException`.

```java
public void processPayment(PaymentDetails details) {
    try {
        paymentGateway.charge(details);
    } catch (PaymentFailedException e) {
        // Log the error
        // Show an error message to the user
    }
}
```

This is a clean way to separate the payment logic from the error-handling logic.

### Creating Your Own Exceptions

You can also create your own custom exceptions by extending the `Exception` class. This is a good way to handle application-specific errors.

```java
// 1. Define your custom exception
class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}

// 2. A method that throws your custom exception
public void placeOrder(Product product, int quantity) throws InsufficientStockException {
    if (product.getStock() < quantity) {
        throw new InsufficientStockException("Not enough stock for " + product.getName());
    }
    // ... proceed with order
}

// 3. Handle your custom exception
try {
    placeOrder(laptop, 1);
} catch (InsufficientStockException e) {
    System.out.println(e.getMessage());
    // Show an error message to the user
}
```

---

## Interview Deep Dives

### Q22: Is a `catch` block always required after a `try` block?

*   **Simple Answer:** No. A `try` block must be followed by either a `catch` block, a `finally` block, or both.
*   **Detailed Explanation:**
    *   `try-catch`: Use this when you want to handle a specific exception.
    *   `try-finally`: Use this when you want to ensure cleanup code runs, regardless of whether an exception occurs.
    *   `try-catch-finally`: Use this when you want to do both.
*   **Modern Best Practice:** For resource cleanup (like files or database connections), you should use the `try-with-resources` statement, which is cleaner and safer than using a `finally` block.

### Q23: How do you manually throw an exception?

*   **Simple Answer:** You use the `throw` keyword, followed by a new instance of an exception object.
*   **The Code:**
    ```java
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }
    ```
*   **Why do this?** To enforce rules and preconditions in your code. If a method is called with invalid arguments or if a business rule is violated, you throw an exception to stop the operation and signal that something is wrong.

### Q24: What is the difference between an `Exception` and an `Error`?

*   **Simple Answer:** You should handle `Exception`s, but you should never try to handle `Error`s.
*   **Detailed Explanation:**
    *   Both inherit from a common parent class called `Throwable`.
    *   **`Exception`:** Represents conditions that a program might want to recover from (e.g., `IOException`, `SQLException`). These are generally predictable problems.
    *   **`Error`:** Represents serious problems that are usually unrecoverable and external to the application (e.g., `OutOfMemoryError`, `StackOverflowError`). Your program has likely entered a state from which it cannot recover.

### Q25: What is the difference between `throw` and `throws`?

*   **Simple Answer:** `throw` is an action that throws an exception. `throws` is a declaration in a method signature that says which exceptions the method might throw.
*   **Detailed Explanation:**
| Keyword | What it is | Example |
| :--- | :--- | :--- |
| `throw` | An action inside a method. | `throw new IOException("File not found");` |
| `throws` | A declaration in a method's signature. | `public void readFile() throws IOException;` |

*   **Key Takeaway:** You use `throw` to make an exception happen. You use `throws` to warn other methods that this exception might happen.

---

[Previous: 05 - Data Structures: Organizing Your Data](../05-Data-Structures/README.md) | [Next: 07 - Java Collections Framework: A Deeper Look](../07-Java-Collections-Framework/README.md)
