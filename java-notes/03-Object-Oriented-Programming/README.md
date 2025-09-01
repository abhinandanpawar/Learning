# 03 - Object-Oriented Programming: Building with Blueprints

Object-Oriented Programming (OOP) was at the heart of our design for Java. We wanted to give you a way to model the real world in your code, to create "objects" that have both data (state) and behavior (methods).

## 1. Classes and Objects: Blueprints and Buildings

*   **Class:** A class is the blueprint. It defines the structure and behavior of a type of object.
*   **Object:** An object is the actual building created from the blueprint. It's an instance of a class.

Let's model a `Product` in our e-commerce application. A good `Product` class should encapsulate its data.

```java
public class Product {
    // Private instance variables (state)
    private String name;
    private double price;

    // Constructor: a special method for creating objects
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Method (behavior)
    public void display() {
        System.out.println(name + ": $" + price);
    }
}
```

When you create an object, you now use the constructor to provide the initial state.

```java
Product laptop = new Product("Laptop", 1200.00);
laptop.display(); // Laptop: $1200.0
```

**JVM Deep Dive: Object Layout on the Heap**

When `new Product()` is executed, the JVM allocates a chunk of memory on the heap for the `Product` object. This memory chunk contains:

1.  **Object Header:** A small amount of metadata about the object, such as a reference to its class and information for the garbage collector.
2.  **Instance Data:** The actual data for the object's fields (`name` and `price`). The `price` (a `double`) is stored directly in the object, while `name` is a reference to a `String` object, which also lives on the heap.

## 2. The Four Pillars of OOP: Our Design Philosophy

We designed Java around four core principles that we believed were essential for building robust and maintainable software.

### a. Encapsulation: The Black Box Principle

We believe that an object should be a "black box". Its internal data should be hidden from the outside world. This is achieved by making fields `private` and providing `public` methods (getters and setters) to access them.

This was a crucial design decision to prevent developers from accidentally corrupting the state of an object.

### b. Inheritance: Standing on the Shoulders of Giants

Inheritance allows you to create a new class that inherits the properties and methods of an existing class. This promotes code reuse.

For our e-commerce app, we could have different types of products:

```java
class Book extends Product {
    String author;
}
```
A `Book` object will have its own fields (`author`) plus the inherited fields (`name`, `price`).

### c. Polymorphism: One Interface, Many Forms

Polymorphism is the ability of an object to take on many forms. The most common use of polymorphism in Java is when a parent class reference is used to refer to a child class object.

**JVM Deep Dive: Virtual Method Tables (vtables)**

How does the JVM know which `display()` method to call if a `Book` object has its own version? This is where the magic of polymorphism comes in, and it's implemented using something called a "virtual method table" or "vtable".

Every class has a vtable that contains the memory addresses of its methods. When you call a method on an object, the JVM looks at the object's vtable to find the correct method to execute. This is how a `Book` object can have a different `display()` method than a generic `Product` object. This is called "runtime polymorphism" because the decision of which method to call is made at runtime.

### d. Abstraction: Hiding the Details

Abstraction is about hiding the implementation details and showing only the essential features. We provided two ways to achieve this: abstract classes and interfaces. We'll dive deeper into these in the next chapter.

---

## Interview Deep Dives

### Q1: What is the difference between an Inner Class and a Sub-Class?

To understand this, let's check this example.

**The Code Example:**
```java
// A Sub-Class uses inheritance (the "is-a" relationship)
class EBook extends Product {
    public EBook(String name, double price) {
        super(name, price);
    }
    // ... EBook specific methods
}

public class Order {
    private long orderId;
    private List<Product> items;

    // An Inner Class has a special relationship with the outer class
    // (the "has-a" relationship, but with special access)
    public class OrderLine {
        private Product product;
        private int quantity;

        public double getLineTotal() {
            // The inner class can access private fields of the outer class!
            System.out.println("Calculating total for order: " + orderId);
            return product.getPrice() * quantity;
        }
    }
}
```

**Detailed Explanation:**

*   **Sub-class (Inheritance):** A sub-class `EBook` **is a** `Product`. It inherits the public and protected members of its parent. This is a fundamental concept for code reuse and polymorphism.
*   **Inner Class:** An inner class `OrderLine` is a class that is nested inside another class, `Order`. It is used as a helper class that is tightly coupled to its outer class. The key feature is that the inner class instance has a special, hidden reference to an instance of the outer class, which allows it to access the outer class's private members (like `orderId`).

**The Principal's Take:**
*   **System Design:** Use **inheritance** when you have a true "is-a" relationship and want to model a hierarchy of types. Use an **inner class** when you have a helper class that only makes sense in the context of its outer class and needs close access to its state. A common use case for inner classes is for implementing event listeners or iterators.

---

### Q5: What is a singleton class? Give a practical example of its usage.

**The Code Example:**
The Singleton pattern ensures that a class has only one instance and provides a global point of access to it.

```java
public class DatabaseConnectionManager {

    // 1. The single instance, created at class loading time.
    private static final DatabaseConnectionManager INSTANCE = new DatabaseConnectionManager();

    // 2. A private constructor to prevent anyone else from creating an instance.
    private DatabaseConnectionManager() {
        // Initialize the database connection here
        System.out.println("Database connection manager initialized.");
    }

    // 3. A public static method to get the single instance.
    public static DatabaseConnectionManager getInstance() {
        return INSTANCE;
    }

    public void connect() {
        System.out.println("Connected to the database.");
    }
}

// Usage:
// DatabaseConnectionManager manager = new DatabaseConnectionManager(); // This will give a compile error!
// DatabaseConnectionManager manager = DatabaseConnectionManager.getInstance();
// manager.connect();
```

**Detailed Explanation:**
The key to the Singleton pattern is a `private` constructor and a `public static` method that returns the single instance. The instance itself is stored in a `private static final` field. This implementation is simple and thread-safe.

**The Principal's Take:**
*   **System Design:** The Singleton pattern should be used with **extreme caution**. While it's useful for managing a truly global resource like a database connection pool or a logging configuration, it can also be an anti-pattern. It introduces global state into your application, which can make code hard to test and reason about.
*   **Trade-offs:** Singletons can make dependency injection and testing difficult. Before you create a singleton, ask yourself: "Can I achieve the same result by creating a regular object and passing it as a dependency to the objects that need it?". In modern frameworks like Spring, the framework manages the lifecycle of your beans as singletons by default, which is a much cleaner approach.

---

[Previous: 02 - Java Basics: The Building Blocks of the Language](../02-Java-Basics/README.md) | [Next: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md)
