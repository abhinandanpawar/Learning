# 03 - Object-Oriented Programming: Building with Blueprints

Object-Oriented Programming (OOP) was at the heart of our design for Java. We wanted to give you a way to model the real world in your code, to create "objects" that have both data (state) and behavior (methods).

## 1. Classes and Objects: Blueprints and Buildings

*   **Class:** A class is the blueprint. It defines the structure and behavior of a type of object.
*   **Object:** An object is the actual building created from the blueprint. It's an instance of a class.

Let's model a `Product` in our e-commerce application:

```java
public class Product {
    // Instance variables (state)
    String name;
    double price;

    // Method (behavior)
    void display() {
        System.out.println(name + ": $" + price);
    }
}
```

When you create an object, you are creating an instance of that class on the heap.

```java
Product laptop = new Product();
laptop.name = "Laptop";
laptop.price = 1200.00;
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

[Previous: 02 - Java Basics: The Building Blocks of the Language](../02-Java-Basics/README.md) | [Next: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md)
