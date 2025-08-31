# 08 - Generics in Java

Generics were introduced in Java 5 to provide type safety and to prevent `ClassCastException`.

## 1. What are Generics?

Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods. This allows you to create a single class, interface, or method that can be used with different types of objects.

Without generics, you would have to use `Object` and then cast the result, which is not type-safe.

## 2. Generic Classes

You can create a generic class by specifying a type parameter in angle brackets `<>`.

```java
// A generic class
public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}

// Usage
Box<Integer> integerBox = new Box<>();
integerBox.set(10);
int value = integerBox.get(); // No cast needed
```

## 3. Generic Methods

You can also create generic methods that can accept any type.

```java
public class Util {
    public static <T> void printArray(T[] inputArray) {
        for (T element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
}
```

## 4. Wildcards

Wildcards are used when you want to work with a generic type but you don't know the exact type.

*   **Upper Bounded Wildcards (`? extends T`):** The wildcard `? extends Number` means that the type can be `Number` or any subclass of `Number` (like `Integer`, `Double`).

*   **Lower Bounded Wildcards (`? super T`):** The wildcard `? super Integer` means that the type can be `Integer` or any superclass of `Integer` (like `Number`, `Object`).

*   **Unbounded Wildcards (`?`):** The wildcard `?` means that the type is unknown.

---

[Previous: 07 - Java Collections Framework](../07-Java-Collections-Framework/README.md) | [Next: 09 - IO Streams](../09-IO-Streams/README.md)
