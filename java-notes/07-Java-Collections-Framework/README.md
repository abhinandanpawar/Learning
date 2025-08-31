# 07 - The Java Collections Framework

The Java Collections Framework provides a set of interfaces and classes to represent and manipulate collections of objects.

## 1. Framework Hierarchy

The framework is based on a hierarchy of interfaces. The main interfaces are:

*   **`Collection`:** The root interface of the collections hierarchy.
*   **`List`:** An ordered collection that allows duplicate elements.
*   **`Set`:** A collection that does not allow duplicate elements.
*   **`Queue`:** A collection used to hold multiple elements prior to processing.
*   **`Map`:** An object that maps keys to values. It is not a true collection (it doesn't extend `Collection`).

## 2. Common Implementations

### `List` Implementations

*   **`ArrayList`:** A resizable array implementation of the `List` interface. Good for random access.
*   **`LinkedList`:** A doubly-linked list implementation of the `List` and `Deque` interfaces. Good for frequent insertions and deletions.

### `Set` Implementations

*   **`HashSet`:** An unordered set that does not allow duplicates. It uses a hash table for storage.
*   **`TreeSet`:** A sorted set that does not allow duplicates. It stores elements in a red-black tree.

### `Map` Implementations

*   **`HashMap`:** An unordered map that does not allow duplicate keys. It uses a hash table for storage.
*   **`TreeMap`:** A sorted map that does not allow duplicate keys. It stores key-value pairs in a red-black tree.

## 3. The `Collections` Utility Class

The `Collections` class provides static methods for operating on or returning collections.

```java
import java.util.ArrayList;
import java.util.Collections;

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(5);
numbers.add(2);
numbers.add(8);

// Sort the list
Collections.sort(numbers); // [2, 5, 8]

// Reverse the list
Collections.reverse(numbers); // [8, 5, 2]

// Find the maximum element
int max = Collections.max(numbers); // 8
```

---

[Previous: 06 - Exception Handling](../06-Exception-Handling/README.md) | [Next: 08 - Generics](../08-Generics/README.md)
