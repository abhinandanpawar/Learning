# 04 - Advanced OOP: Interfaces and Abstraction

Now that you understand the core principles of OOP, let's explore some of the more advanced tools we gave you to design flexible and maintainable software.

## 1. Abstraction: The Power of Not Knowing

Abstraction is a powerful concept. It allows you to define a contract without specifying the implementation. We provided two tools for this: abstract classes and interfaces.

### a. Abstract Classes: A Template for a Class

An abstract class is a class that cannot be instantiated. It's a template that other classes can extend.

In our e-commerce app, we might want to have a base `Product` class that has some common functionality, but we don't want anyone to be able to create a generic `Product`.

```java
public abstract class Product {
    // ... common fields and methods
    public abstract void display(); // Abstract method
}
```

### b. Interfaces: A Pure Contract

An interface is a completely abstract type. It only contains method signatures. We designed interfaces to solve the "multiple inheritance" problem that plagues languages like C++. A class can implement multiple interfaces, allowing it to wear many hats.

For our e-commerce app, we could have an interface for items that can be shipped:

```java
public interface Shippable {
    double getWeight();
    String getShippingAddress();
}
```

A `Book` could implement `Shippable`, but a `DigitalDownload` would not.

**System Design Insight:** Interfaces are the cornerstone of "loose coupling" in system design. By programming to an interface rather than a concrete class, you can easily swap out implementations without changing the rest of your code. This is a key principle for building flexible systems.

## 2. Packages: Organizing Your Code

As your application grows, you need a way to organize your code. We created packages for this purpose. A package is a namespace that organizes a set of related classes and interfaces.

For our e-commerce app, we might have packages like:
*   `com.example.ecommerce.products`
*   `com.example.ecommerce.orders`
*   `com.example.ecommerce.users`

This was a simple but crucial design decision to avoid naming conflicts and to make code easier to navigate.

## 3. Enums: Type-Safe Constants

Before Java 5, developers often used integers or strings to represent a set of constants, which was error-prone. We introduced `enum` to create a type-safe way to represent a fixed set of values.

For our e-commerce app, we could have an enum for order status:

```java
public enum OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
```

This ensures that an order's status can only be one of these four values, which makes the code more robust.

---

## Interview Deep Dives

### Q18: What's the difference between an Abstract Class and an Interface in Java?

To understand this, let's check this example.

**The Code Example:**
```java
// An interface defines a pure contract of "what" a class can do.
// It cannot have instance variables.
interface Shippable {
    // Methods are public and abstract by default.
    double getWeight();
    String getShippingAddress();

    // Since Java 8, you can have default methods with implementation.
    default String getShippingLabel() {
        return "Ship to: " + getShippingAddress();
    }
}

// An abstract class provides a partial implementation and can have state.
abstract class BaseProduct {
    protected String sku; // Can have instance variables (state)

    public BaseProduct(String sku) { // Can have constructors
        this.sku = sku;
    }

    public String getSku() { // Can have concrete methods
        return sku;
    }

    public abstract double getPrice(); // Can have abstract methods
}

// A concrete class can extend one abstract class and implement many interfaces.
class Book extends BaseProduct implements Shippable {
    // ... implementation
}
```

**Detailed Explanation & Trade-offs:**
This is a classic interview question that tests your understanding of core OOP design principles.

| Feature | Abstract Class | Interface |
|---|---|---|
| **Purpose** | To provide a common base with some shared implementation and state. | To define a contract that a class must adhere to. |
| **State** | Can have instance variables. | Cannot have instance variables. |
| **Constructors** | Can have constructors. | Cannot have constructors. |
| **Methods** | Can have a mix of abstract and concrete methods. | All methods are `public` by default. Can have `abstract`, `default`, and `static` methods. |
| **Inheritance** | A class can `extend` only **one** abstract class. | A class can `implement` **multiple** interfaces. |

**The Principal's Take:**
*   **System Design:** **"Favor composition over inheritance"** is a good design principle. This often means you should **favor interfaces**. When you program to an interface, you are decoupling your code from the implementation, which makes it more flexible.
*   Use an **abstract class** when you have a group of related classes that share a significant amount of common code and state. A good example is `AbstractList` in the Java Collections Framework, which provides a skeletal implementation of the `List` interface.
*   Use an **interface** when you want to define a capability that can be implemented by unrelated classes. `Shippable`, `Serializable`, and `Comparable` are great examples of this.

---

### Q17: Can we declare a class as Abstract without having any abstract method?

Yes. To understand why, let's check this example.

**The Code Example:**
```java
// This is a valid abstract class, even with no abstract methods.
public abstract class BaseController {
    protected void logRequest() {
        // common logging logic
    }

    // Other common helper methods can go here...
}

// You cannot do this:
// BaseController controller = new BaseController(); // Compile error!
```

**Detailed Explanation:**
Declaring a class `abstract` means one thing: **it cannot be instantiated**. You cannot create an object of that class using the `new` keyword.

**The Principal's Take:**
*   **System Design:** Why would you do this? This is a design choice to signal that a class is incomplete and is only intended to be used as a **base class for other classes to extend**. It prevents other developers from using your base class directly. For example, you might have a `BaseController` in a web framework that provides common helper methods, but it doesn't make sense to create an instance of the `BaseController` itself. Only concrete controllers like `UserController` or `ProductController` should be instantiated.

---

[Previous: 03 - Object-Oriented Programming: Building with Blueprints](../03-Object-Oriented-Programming/README.md) | [Next: 05 - Data Structures: Organizing Your Data](../05-Data-Structures/README.md)
