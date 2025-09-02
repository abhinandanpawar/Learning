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

### Q: What is a Constructor? Can it be overloaded?

**The Code Example:**
```java
public class Product {
    private String name;
    private double price;

    // Default constructor (no arguments)
    public Product() {
        this.name = "Default Product";
        this.price = 0.0;
    }

    // Parameterized constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Copy constructor
    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
    }
}
```

**Detailed Explanation:**
A **constructor** is a special method that is called when an object is created (`new`). Its primary purpose is to initialize the object's state.

*   **Rules for constructors:**
    *   A constructor's name must be the same as the class name.
    *   A constructor cannot have an explicit return type.
*   **Default Constructor:** If you don't define any constructor in a class, the Java compiler will create a default, no-argument constructor for you.
*   **Constructor Overloading:** Just like methods, constructors can be overloaded. This means you can have multiple constructors in a class, as long as they have different parameter lists. This provides different ways to create and initialize an object.
*   **Copy Constructor:** A copy constructor is a constructor that takes another object of the same class as an argument and initializes the new object with the values from the existing object. While Java doesn't provide an automatic copy constructor like C++, you can write one yourself, as shown in the example.

**The Principal's Take:**
Constructors are fundamental to OOP in Java. They ensure that an object is in a valid state as soon as it's created. Using constructor overloading is a clean way to provide flexible object creation. While copy constructors are less common in idiomatic Java (people often use `clone()` or factory methods instead), understanding the pattern is important.

---

### Q: What is the difference between Method Overloading and Method Overriding?

This is a fundamental question about polymorphism in Java.

**The Principal's Take:** Overloading is about having multiple methods with the same name but different signatures in the same class. Overriding is about a subclass providing a specific implementation for a method that is already defined in its superclass.

| Feature | Method Overloading (Compile-time Polymorphism) | Method Overriding (Runtime Polymorphism) |
|---|---|---|
| **Purpose** | To provide different ways to call a method with different arguments. | To provide a specific implementation of a method in a subclass. |
| **Scope** | Occurs within the same class. | Occurs between a superclass and a subclass. |
| **Signature** | Methods must have different signatures (different number or type of parameters). | Methods must have the same signature (same name, number, and type of parameters). |
| **Return Type** | Can be different. | Must be the same or a covariant type. |
| **`static` methods** | Can be overloaded. | Cannot be overridden (this is called method hiding). |

**The Code Example:**
```java
class Shape {
    // Overloaded method
    public void draw() {
        System.out.println("Drawing a generic shape.");
    }

    public void draw(String color) {
        System.out.println("Drawing a " + color + " shape.");
    }

    public double getArea() {
        return 0.0;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) { this.radius = radius; }

    // Overriding the getArea method
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
```

**Detailed Explanation:**

*   **Overloading:** In the `Shape` class, the `draw()` method is overloaded. We have two versions: one with no arguments and one that takes a `String`. The compiler decides which one to call based on the arguments you provide at compile time.
*   **Overriding:** The `Circle` class `extends` `Shape` and provides its own version of the `getArea()` method. When you call `getArea()` on a `Circle` object, the JVM uses the object's actual type at runtime to decide which version of the method to execute (the one in `Circle`). The `@Override` annotation is not required, but it's a best practice as it tells the compiler to check that you are actually overriding a method from the superclass.

**System Design Insight:**
*   **Overloading** is a form of "syntactic sugar" that makes an API more convenient to use.
*   **Overriding** is the mechanism that enables runtime polymorphism, which is a cornerstone of flexible and extensible object-oriented design. It allows you to write code that operates on a general type (like `Shape`) while correctly executing the specific behavior of a concrete type (like `Circle`).

---

### Q: What are the different types of inheritance? Why doesn't Java support multiple inheritance?

Inheritance is a fundamental pillar of OOP, but it's important to understand its different forms and limitations.

**The Principal's Take:** We made a conscious design decision to exclude multiple inheritance of classes from Java. This was to avoid the complexity and ambiguity of the "Diamond Problem". We provided interfaces as a cleaner way to achieve a similar goal.

**Types of Inheritance:**

*   **Single Inheritance:** A class can inherit from only one superclass. This is the model that Java follows.
*   **Multilevel Inheritance:** A class can inherit from a class that itself inherits from another class (e.g., `C` extends `B`, and `B` extends `A`). This is supported in Java.
*   **Hierarchical Inheritance:** Multiple classes can inherit from a single superclass (e.g., `B` extends `A`, and `C` extends `A`). This is also supported in Java.
*   **Multiple Inheritance:** A class inherits from multiple superclasses. **Java does not support this for classes.**
*   **Hybrid Inheritance:** A mix of two or more of the above types of inheritance. Since Java doesn't support multiple inheritance, it doesn't support hybrid inheritance that involves multiple inheritance.

**The "Diamond Problem": Why Multiple Inheritance is Tricky**

Imagine two classes, `B` and `C`, that both inherit from a class `A`. And a class `D` inherits from both `B` and `C`. If `A` has a method `foo()`, and both `B` and `C` override it, which version of `foo()` should `D` inherit? This ambiguity is known as the Diamond Problem.

```
      A
     / \
    B   C
     \ /
      D
```

**Java's Solution: Interfaces**
We solved this by allowing a class to `implement` multiple interfaces. An interface defines a contract of methods, but it doesn't provide an implementation for instance methods (prior to Java 8's default methods). This means there is no ambiguity about which implementation to inherit. A class can promise to fulfill multiple contracts (interfaces) while only inheriting the implementation from one superclass.

**System Design Insight:**
*   **Favor Composition over Inheritance:** This is a common design principle. Instead of inheriting from a class to get its functionality, you can hold an instance of that class as a field. This is often more flexible and less coupled.
*   **Use Interfaces for Contracts:** Use interfaces to define the "what" (the contract), and use classes to define the "how" (the implementation). This leads to a more flexible and testable design.

---

[Previous: 02 - Java Basics: The Building Blocks of the Language](../02-Java-Basics/README.md) | [Next: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md)
