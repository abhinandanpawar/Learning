# 04 - Advanced Object-Oriented Programming

This section delves into more advanced concepts of Object-Oriented Programming in Java.

## 1. Interfaces

An interface in Java is a blueprint of a class. It has static constants and abstract methods. It is a mechanism to achieve abstraction and multiple inheritance in Java.

```java
// Interface definition
interface Drivable {
    void drive(); // Abstract method (no body)
}

// Class implementing the interface
class Car implements Drivable {
    public void drive() {
        System.out.println("The car is driving.");
    }
}
```

A class can implement multiple interfaces.

## 2. Abstract Classes vs. Interfaces

| Feature              | Abstract Class                               | Interface                                    |
| -------------------- | -------------------------------------------- | -------------------------------------------- |
| **Methods**          | Can have both abstract and non-abstract methods. | Can only have abstract methods (Java 8+ allows default and static methods). |
| **Variables**        | Can have final, non-final, static, and non-static variables. | Can only have `public static final` variables. |
| **Inheritance**      | A class can extend only one abstract class.  | A class can implement multiple interfaces.   |
| **Constructors**     | Can have constructors.                       | Cannot have constructors.                    |
| **Access Modifiers** | Can have public, protected, and private members. | Members are `public` by default.             |

## 3. Packages

Packages are used to group related classes. They help in avoiding naming conflicts and provide controlled access.

To create a package, you use the `package` keyword at the beginning of your Java source file.

```java
// in file com/example/MyClass.java
package com.example;

public class MyClass {
    // ...
}
```

To use a class from another package, you can use the `import` keyword.

```java
import com.example.MyClass;

public class AnotherClass {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
    }
}
```

## 4. Enums

An enum is a special "class" that represents a group of constants (unchangeable variables, like `final` variables).

```java
enum Level {
    LOW,
    MEDIUM,
    HIGH
}

public class Main {
    public static void main(String[] args) {
        Level myVar = Level.MEDIUM;
        System.out.println(myVar); // Output: MEDIUM
    }
}
```

---

[Previous: 03 - Object-Oriented Programming](../03-Object-Oriented-Programming/README.md) | [Next: 05 - Data Structures](../05-Data-Structures/README.md)
