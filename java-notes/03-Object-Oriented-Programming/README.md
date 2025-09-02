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

### Q10: What is the difference between an Inner Class and a Sub-Class?

*   **Simple Answer:** A sub-class inherits from a parent class (an "is-a" relationship). An inner class is a helper class defined inside another class.
*   **Detailed Explanation:**
    *   **Sub-class (e.g., `class EBook extends Product`):** This is standard inheritance. `EBook` *is a* `Product` and inherits its public/protected members. It's used for code reuse and polymorphism.
    *   **Inner Class (e.g., `class OrderLine` inside `class Order`):** This is a class defined within another class. It's used for helper classes that are tightly coupled to the outer class. The key feature is that an inner class object has access to the private fields of its outer class object.

### Q11: What is a singleton class?

*   **Simple Answer:** A class that is designed to have only one instance.
*   **How it works:**
    1.  Make the constructor `private`.
    2.  Create a `private static final` instance of the class.
    3.  Provide a `public static` method (`getInstance()`) to return that single instance.
*   **When to use it:** For global resources like a database connection manager or a configuration loader.
*   **Caution:** Singletons can make code hard to test because they introduce global state. Modern frameworks like Spring often provide better ways to manage single instances using dependency injection.

### Q12: What is a constructor and can it be overloaded?

*   **Simple Answer:** A constructor is a special method used to create an object. And yes, it can be overloaded.
*   **Detailed Explanation:**
    *   A constructor must have the same name as the class and has no return type.
    *   Its job is to initialize the object's fields.
    *   **Overloading:** You can have multiple constructors in the same class, as long as they have different parameters. This gives you flexible ways to create objects.

### Q13: What is the difference between Method Overloading and Method Overriding?

*   **Simple Answer:** Overloading is having multiple methods with the same name but different parameters in the *same class*. Overriding is when a *child class* provides a new implementation for a method from its parent class.
*   **Detailed Explanation:**
| Feature | Method Overloading | Method Overriding |
| :--- | :--- | :--- |
| **Location** | Same class | Parent and Child class |
| **Parameters**| Must be different | Must be the same |
| **Purpose** | Convenience (multiple ways to call a method) | Polymorphism (changing behavior in a child class) |

### Q14: Why doesn't Java support multiple inheritance for classes?

*   **Simple Answer:** To avoid the "Diamond Problem".
*   **The Diamond Problem:**
    *   Imagine class `A` has a method `foo()`.
    *   Classes `B` and `C` both inherit from `A` and override `foo()`.
    *   If class `D` could inherit from both `B` and `C`, which version of `foo()` would it get? It's ambiguous.
*   **Java's Solution:** A class can only `extend` one parent class. However, a class can `implement` multiple interfaces, which is how Java achieves a safe form of multiple inheritance for behavior.

---

[Previous: 02 - Java Basics: The Building Blocks of the Language](../02-Java-Basics/README.md) | [Next: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md)
