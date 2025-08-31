# 03 - Object-Oriented Programming (OOP) in Java

Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects", which can contain data in the form of fields (often known as attributes or properties) and code in the form of procedures (often known as methods).

## 1. Classes and Objects

*   **Class:** A blueprint for creating objects. It defines the properties and methods that the objects of that class will have.
*   **Object:** An instance of a class.

```java
// Class definition
public class Dog {
    // Properties
    String breed;
    int age;
    String color;

    // Method
    void bark() {
        System.out.println("Woof! Woof!");
    }
}

// Creating objects (instances of the Dog class)
Dog myDog = new Dog();
myDog.breed = "Golden Retriever";
myDog.age = 3;
myDog.color = "Golden";
myDog.bark(); // Output: Woof! Woof!
```

## 2. The Four Pillars of OOP

### a. Encapsulation

Encapsulation is the bundling of data (attributes) and methods that operate on the data into a single unit (a class). It also involves restricting access to some of an object's components, which is known as information hiding.

This is typically achieved by making the attributes `private` and providing `public` getter and setter methods to access and modify them.

```java
public class Person {
    private String name;
    private int age;

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for age...
}
```

### b. Inheritance

Inheritance is a mechanism in which one class acquires the properties and behaviors (methods) of another class. The class that inherits is called the subclass (or child class), and the class from which it inherits is called the superclass (or parent class).

```java
// Superclass
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

// Subclass
class Dog extends Animal {
    void bark() {
        System.out.println("The dog barks.");
    }
}

// Usage
Dog dog = new Dog();
dog.eat(); // Inherited from Animal class
dog.bark();
```

### c. Polymorphism

Polymorphism means "many forms". In OOP, it refers to the ability of a variable, function, or object to take on multiple forms.

*   **Method Overloading (Compile-time Polymorphism):** Multiple methods with the same name but different parameters.

    ```java
    class Calculator {
        int add(int a, int b) {
            return a + b;
        }

        double add(double a, double b) {
            return a + b;
        }
    }
    ```

*   **Method Overriding (Runtime Polymorphism):** A subclass provides a specific implementation of a method that is already provided by its superclass.

    ```java
    class Animal {
        void makeSound() {
            System.out.println("Some generic sound");
        }
    }

    class Dog extends Animal {
        @Override
        void makeSound() {
            System.out.println("Woof! Woof!");
        }
    }
    ```

### d. Abstraction

Abstraction is the concept of hiding the complex implementation details and showing only the essential features of the object. It is achieved using abstract classes and interfaces.

*   **Abstract Class:** A class that cannot be instantiated and may contain abstract methods (methods without a body).

    ```java
    abstract class Shape {
        abstract void draw(); // Abstract method
    }

    class Circle extends Shape {
        void draw() {
            System.out.println("Drawing a circle");
        }
    }
    ```

---

[Previous: 02 - Java Basics](../02-Java-Basics/README.md) | [Next: 04 - Advanced OOP](../04-Advanced-OOP/README.md)
