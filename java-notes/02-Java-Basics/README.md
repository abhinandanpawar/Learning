# 02 - Java Basics

This section covers the fundamental building blocks of the Java language.

## 1. Variables and Data Types

A variable is a container that holds a value. In Java, you must declare a variable before you can use it.

### Data Types

Java has two categories of data types:

*   **Primitive Data Types:** These are the most basic data types available in Java.
    *   `byte`: 8-bit integer.
    *   `short`: 16-bit integer.
    *   `int`: 32-bit integer.
    *   `long`: 64-bit integer.
    *   `float`: 32-bit floating-point number.
    *   `double`: 64-bit floating-point number.
    *   `char`: 16-bit Unicode character.
    *   `boolean`: `true` or `false`.

*   **Reference Data Types:** These variables refer to objects.
    *   `String`: A sequence of characters.
    *   Arrays, Classes, Interfaces, etc.

### Declaring Variables

```java
// Primitive types
int age = 30;
double salary = 60000.50;
char grade = 'A';
boolean isStudent = true;

// Reference type
String name = "John Doe";
```

## 2. Operators

Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result.

*   **Arithmetic Operators:** `+`, `-`, `*`, `/`, `%` (modulo)
*   **Relational Operators:** `==`, `!=`, `>`, `<`, `>=`, `<=`
*   **Logical Operators:** `&&` (AND), `||` (OR), `!` (NOT)
*   **Assignment Operators:** `=`, `+=`, `-=`, `*=`, `/=`, `%=`

## 3. Control Flow

Control flow statements regulate the order in which the statements in a program are executed.

### `if-else` Statement

```java
int score = 85;

if (score >= 90) {
    System.out.println("Excellent!");
} else if (score >= 80) {
    System.out.println("Very Good!");
} else {
    System.out.println("Good.");
}
```

### `switch` Statement

```java
char grade = 'B';

switch (grade) {
    case 'A':
        System.out.println("Excellent!");
        break;
    case 'B':
        System.out.println("Very Good!");
        break;
    default:
        System.out.println("Good.");
}
```

### `for` Loop

```java
for (int i = 0; i < 5; i++) {
    System.out.println("i = " + i);
}
```

### `while` Loop

```java
int i = 0;
while (i < 5) {
    System.out.println("i = " + i);
    i++;
}
```

### `do-while` Loop

```java
int i = 0;
do {
    System.out.println("i = " + i);
    i++;
} while (i < 5);
```

---

[Previous: 01 - Getting Started](../01-Getting-Started/README.md) | [Next: 03 - Object-Oriented Programming](../03-Object-Oriented-Programming/README.md)
