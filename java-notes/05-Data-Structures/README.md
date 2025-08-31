# 05 - Data Structures in Java

Data structures are used to store and organize data. Java provides a rich set of data structures in its Collections Framework.

## 1. Arrays

An array is a container object that holds a fixed number of values of a single type. The length of an array is established when the array is created.

```java
// Declare and initialize an array of integers
int[] numbers = {10, 20, 30, 40, 50};

// Access an element
int firstNumber = numbers[0]; // 10

// Get the length of the array
int length = numbers.length; // 5
```

## 2. ArrayList

An `ArrayList` is a resizable array. It grows its size to accommodate new elements and shrinks the size when the elements are removed.

```java
import java.util.ArrayList;

ArrayList<String> names = new ArrayList<>();

// Add elements
names.add("Alice");
names.add("Bob");
names.add("Charlie");

// Access an element
String firstName = names.get(0); // "Alice"

// Get the size
int size = names.size(); // 3
```

## 3. LinkedList

A `LinkedList` is a linear data structure where the elements are not stored in contiguous locations. The elements are linked using pointers. It is very efficient for insertions and deletions.

```java
import java.util.LinkedList;

LinkedList<String> cars = new LinkedList<>();

// Add elements
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

// Add an element at the beginning
cars.addFirst("Mazda");
```

## 4. HashMap

A `HashMap` stores items in "key/value" pairs. It is an unordered collection and does not allow duplicate keys.

```java
import java.util.HashMap;

HashMap<String, Integer> studentScores = new HashMap<>();

// Add key-value pairs
studentScores.put("Alice", 95);
studentScores.put("Bob", 80);

// Access a value by key
int aliceScore = studentScores.get("Alice"); // 95

// Check if a key exists
boolean hasBob = studentScores.containsKey("Bob"); // true
```

---

[Previous: 04 - Advanced OOP](../04-Advanced-OOP/README.md) | [Next: 06 - Exception Handling](../06-Exception-Handling/README.md)
