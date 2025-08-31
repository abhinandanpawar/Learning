# 01 - Getting Started with Java

This section will guide you through setting up your Java development environment and writing your first Java program.

## 1. Setting up the Java Development Kit (JDK)

The JDK is a software development environment used for developing Java applications. It includes the Java Runtime Environment (JRE), a compiler, an archiver, and other tools.

You can download the latest version of the JDK from the official Oracle website or from the OpenJDK project.

*   **Oracle JDK:** [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
*   **OpenJDK:** [https://openjdk.java.net/](https://openjdk.java.net/)

Follow the installation instructions for your operating system (Windows, macOS, or Linux). After installation, you should verify the installation by opening a terminal or command prompt and running the following commands:

```bash
java -version
javac -version
```

These commands should print the installed Java and Javac versions.

## 2. Choosing an Integrated Development Environment (IDE)

An IDE is a software application that provides comprehensive facilities to computer programmers for software development. An IDE normally consists of at least a source code editor, build automation tools, and a debugger.

While you can write Java code in any text editor, using an IDE can significantly improve your productivity. Here are some popular IDEs for Java development:

*   **IntelliJ IDEA:** A powerful and popular IDE with a free community edition.
*   **Eclipse:** A widely used open-source IDE.
*   **Visual Studio Code:** A lightweight and extensible code editor with excellent Java support through extensions.

For beginners, we recommend starting with IntelliJ IDEA Community Edition or Visual Studio Code with the "Extension Pack for Java".

## 3. Writing Your First Java Program ("Hello, World!")

Let's write a simple program that prints "Hello, World!" to the console. Create a new file named `HelloWorld.java` and add the following code:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

**Explanation:**

*   `public class HelloWorld`: This defines a class named `HelloWorld`. In Java, all code must reside inside a class.
*   `public static void main(String[] args)`: This is the main method, which is the entry point of any Java application.
*   `System.out.println("Hello, World!");`: This line prints the text "Hello, World!" to the console.

## 4. Compiling and Running Your Program

To compile your program, open a terminal or command prompt, navigate to the directory where you saved `HelloWorld.java`, and run the following command:

```bash
javac HelloWorld.java
```

This will create a new file named `HelloWorld.class`, which contains the compiled bytecode.

To run your program, use the `java` command followed by the class name:

```bash
java HelloWorld
```

You should see the following output on your console:

```
Hello, World!
```

Congratulations! You have successfully written and executed your first Java program.

---

[Previous: 00 - Introduction](../00-Introduction/README.md) | [Next: 02 - Java Basics](../02-Java-Basics/README.md)
