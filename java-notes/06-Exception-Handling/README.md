# 06 - Exception Handling in Java

Exception handling is a mechanism to handle runtime errors such as `ClassNotFoundException`, `IOException`, `SQLException`, etc.

## 1. What is an Exception?

An exception is an event that disrupts the normal flow of the program. It is an object which is thrown at runtime.

## 2. The `try-catch` Block

The `try-catch` block is used to handle exceptions.

*   **`try`:** The `try` block contains the set of statements where an exception can occur.
*   **`catch`:** The `catch` block is used to handle the exception.

```java
try {
    // Code that may throw an exception
    int result = 10 / 0; // This will throw an ArithmeticException
} catch (ArithmeticException e) {
    // Handle the exception
    System.out.println("Cannot divide by zero!");
}
```

## 3. The `finally` Block

The `finally` block is always executed, whether an exception is handled or not. It is used to execute important code such as closing a file or a database connection.

```java
try {
    // ...
} catch (Exception e) {
    // ...
} finally {
    System.out.println("This will always be printed.");
}
```

## 4. Checked vs. Unchecked Exceptions

*   **Checked Exceptions:** These are exceptions that are checked at compile-time. If a method throws a checked exception, it must be handled either by a `try-catch` block or by declaring it in the method signature with the `throws` keyword. Examples: `IOException`, `SQLException`.

*   **Unchecked Exceptions (Runtime Exceptions):** These are exceptions that are not checked at compile-time. They usually occur due to programming errors. Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`.

## 5. The `throw` and `throws` Keywords

*   **`throw`:** The `throw` keyword is used to explicitly throw an exception.

    ```java
    public void validate(int age) {
        if (age < 18) {
            throw new ArithmeticException("Person is not eligible to vote");
        }
    }
    ```

*   **`throws`:** The `throws` keyword is used to declare an exception. It gives an indication to the programmer that there may be an exception.

    ```java
    public void myMethod() throws IOException {
        // Code that may throw IOException
    }
    ```

---

[Previous: 05 - Data Structures](../05-Data-Structures/README.md) | [Next: 07 - Java Collections Framework](../07-Java-Collections-Framework/README.md)
