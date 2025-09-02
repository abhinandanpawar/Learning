# 17 - Unit Testing with JUnit

Unit testing is the process of testing individual units of source code (e.g., methods, classes) to determine if they are fit for use. It's a fundamental practice in modern software development that helps ensure code quality and prevent regressions.

## Why is Unit Testing Important?
*   **Improves Code Quality:** Writing tests forces you to think about the design of your code and can lead to better, more modular designs.
*   **Catches Bugs Early:** Unit tests can catch bugs early in the development process, when they are easier and cheaper to fix.
*   **Provides a Safety Net for Refactoring:** A good suite of unit tests gives you the confidence to refactor and improve your code, knowing that you will be alerted if you break something.
*   **Serves as Documentation:** Unit tests can serve as a form of executable documentation that shows how a piece of code is intended to be used.

## JUnit: The Standard for Java Unit Testing
JUnit is the most popular unit testing framework for Java. It provides a simple and powerful way to write and run tests.

### A Simple JUnit Example
Let's say we have a simple `Calculator` class:
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

Here's how you would write a JUnit test for the `add` method:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }
}
```

**Explanation:**
*   **`@Test`:** This annotation marks a method as a test method.
*   **`assertEquals(expected, actual, message)`:** This is an "assertion" method from JUnit. It checks if the `actual` result of the code is equal to the `expected` value. If it's not, the test fails and the `message` is displayed.

To run this test, you would typically use a build tool like Maven or Gradle, which have built-in support for running JUnit tests.

This is just a very basic introduction. Modern unit testing also involves concepts like mocking (using libraries like Mockito) to isolate the unit of code you are testing from its dependencies.
