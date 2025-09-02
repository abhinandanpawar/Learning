# 05 - Data Structures: Organizing Your Data

As language designers, we knew we had to provide you with a powerful and flexible set of tools for organizing your data. This is where the Java Collections Framework comes in. It's a set of data structures that we designed to handle common programming tasks.

Let's look at a few of the most important ones, and the design trade-offs we made for each.

## 1. `ArrayList`: The Go-To List

An `ArrayList` is a resizable array. It's probably the most common data structure you'll use.

In our e-commerce app, we could use an `ArrayList` to store the products in a shopping cart.

```java
// Assuming Product class from previous chapter
Product laptop = new Product("Laptop", 1200.00);
Product book = new Product("Java for Kids", 25.00);

ArrayList<Product> shoppingCart = new ArrayList<>();
shoppingCart.add(laptop);
shoppingCart.add(book);

// Iterate over the shopping cart
for (Product product : shoppingCart) {
    product.display();
}

// Remove an item
shoppingCart.remove(book);

System.out.println("Cart size: " + shoppingCart.size()); // 1
```

**Memory and Performance Deep Dive:**

*   **Memory:** An `ArrayList` is backed by a plain old array on the heap.
*   **Performance:** Accessing an element by its index (`shoppingCart.get(0)`) is very fast (O(1) time complexity). However, adding or removing an element from the middle of the list can be slow (O(n)) because it requires shifting all the subsequent elements.
*   **Resizing:** When you add an element to a full `ArrayList`, we have to create a new, larger array and copy all the elements over. This can be an expensive operation.

**System Design Insight:** `ArrayList` is a great choice when you need fast random access to elements and you don't do a lot of insertions and deletions in the middle of the list.

## 2. `LinkedList`: The Flexible List

A `LinkedList` is a sequence of nodes, where each node points to the next one.

**Memory and Performance Deep Dive:**

*   **Memory:** Each node in a `LinkedList` is a separate object on the heap, which means it has more memory overhead than an `ArrayList`.
*   **Performance:** Adding or removing an element from a `LinkedList` is very fast (O(1)) if you have a reference to the node. However, accessing an element by index is slow (O(n)) because we have to traverse the list from the beginning.

**System Design Insight:** `LinkedList` is a good choice when you have a lot of insertions and deletions, especially at the beginning or end of the list.

## 3. `HashMap`: The "Key-Value" Store

A `HashMap` stores key-value pairs. It's incredibly useful for looking up values by a key.

In our e-commerce app, we could use a `HashMap` to store the quantity of each product in the shopping cart.

```java
HashMap<Product, Integer> cartQuantities = new HashMap<>();
cartQuantities.put(laptop, 1);
cartQuantities.put(book, 2);

// Get the quantity of a product
int laptopQuantity = cartQuantities.get(laptop); // 1

// Update the quantity
cartQuantities.put(laptop, laptopQuantity + 1);

// Iterate over the keys
for (Product product : cartQuantities.keySet()) {
    System.out.println(product.getName());
}

// Iterate over the values
for (Integer quantity : cartQuantities.values()) {
    System.out.println(quantity);
}
```

**Memory and Performance Deep Dive:**

*   **Memory:** A `HashMap` uses an array of linked lists (or trees, since Java 8) to store its data. It has a higher memory overhead than a simple list.
*   **Performance:** `HashMap` provides constant-time performance (O(1)) for `get` and `put` operations, assuming the hash function disperses the elements properly among the buckets. This is why it's so fast.

**System Design Insight:** `HashMap` is the perfect tool when you need to quickly look up a value by a key. It's one of the most important data structures for system design.

---

## Interview Deep Dives

### Q34 & Q91: What's the difference between an Array and a `Vector`? How do you increase an array's size?

To understand this, let's look at the evolution of dynamic arrays in Java.

**The Code Example:**
```java
import java.util.Arrays;
import java.util.Vector;
import java.util.ArrayList;

public class ArrayEvolution {
    public static void main(String[] args) {
        // 1. Plain Array: Fixed size.
        String[] plainArray = new String[2];
        plainArray[0] = "A";
        plainArray[1] = "B";
        // plainArray[2] = "C"; // This would throw an ArrayIndexOutOfBoundsException

        // 2. Vector: The "old way". Synchronized and slow.
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");

        // 3. ArrayList: The "new way". Not synchronized and fast.
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
    }
}
```

**Detailed Explanation:**

*   **Array:** An array is a fixed-size data structure. Once you create it, you **cannot change its size**. If you need a larger array, you must create a new one and copy the elements from the old one.
    *   **Inline Initialization:** You can initialize an array with a set of values when you declare it: `String[] names = {"Alice", "Bob", "Charlie"};`
    *   **Multi-dimensional Arrays:** You can create arrays of arrays, which are useful for representing grids or matrices: `int[][] matrix = new int[3][3];`
*   **`Vector`:** `Vector` was one of the original collection classes in Java 1.0. It's essentially a resizable array. Its key characteristic is that all of its public methods are `synchronized`. This means it is thread-safe, but it also means it's slow, as every operation requires acquiring a lock.
*   **`ArrayList`:** `ArrayList` was introduced in Java 1.2 as a replacement for `Vector`. It is also a resizable array, but its methods are **not synchronized**.

**The Principal's Take:**
*   **System Design:** **You should almost never use `Vector` in new code.** It's considered a legacy class. If you need thread safety, you should use a modern alternative like `ConcurrentHashMap` or wrap a collection using `Collections.synchronizedList()`. For single-threaded access, `ArrayList` is the clear winner due to its better performance. The "dynamic" or "resizable" nature of `ArrayList` and `Vector` is a perfect answer to "How can we increase the size of an array?". You don't. You use a better data structure.

---

### Q54: What's the difference between a Stack and a Queue?

To understand this, let's look at how they handle data.

**The Code Example:**
```java
import java.util.Stack; // Legacy class, avoid!
import java.util.Queue;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

public class StackVsQueue {
    public static void main(String[] args) {
        // Stack: LIFO (Last-In, First-Out)
        // Principal's take: Don't use Stack. Use a Deque instead.
        Deque<String> stack = new ArrayDeque<>();
        stack.push("A"); // A
        stack.push("B"); // B, A
        stack.push("C"); // C, B, A
        System.out.println("Stack pop: " + stack.pop()); // C
        System.out.println("Stack pop: " + stack.pop()); // B

        // Queue: FIFO (First-In, First-Out)
        Queue<String> queue = new LinkedList<>();
        queue.add("A"); // A
        queue.add("B"); // A, B
        queue.add("C"); // A, B, C
        System.out.println("Queue poll: " + queue.poll()); // A
        System.out.println("Queue poll: " + queue.poll()); // B
    }
}
```

**Detailed Explanation:**

*   **Stack (LIFO):** A stack follows the "Last-In, First-Out" principle. The last item you add (`push`) is the first item you remove (`pop`). Think of a stack of plates.
    *   **Common Methods:** `push(item)`, `pop()`, `peek()`, `isEmpty()`.
*   **Queue (FIFO):** A queue follows the "First-In, First-Out" principle. The first item you add is the first item you remove. Think of a line at a ticket counter.
    *   **Common Methods:** `add(item)`, `poll()`, `peek()`, `isEmpty()`.

**The Principal's Take:**
*   **System Design:** Stacks are useful for problems involving recursion, parsing expressions, or maintaining a history (like the "back" button in a browser). Queues are fundamental in system design for handling tasks in the order they were received, such as in message queues (like RabbitMQ or SQS) or for managing requests in a web server.
*   **Implementation:** The original `java.util.Stack` class is a legacy class that extends `Vector`, so it's synchronized and slow. **Do not use it.** The modern way to implement a stack is to use any class that implements the `Deque` (Double-Ended Queue) interface, such as `ArrayDeque`. For a queue, `LinkedList` or `ArrayDeque` are both good choices.

---

### Q: How would you sort an array of 0s, 1s, and 2s?

This is a classic interview question that tests your understanding of sorting algorithms beyond the standard library `sort()` methods. It's a stand-in for any situation where you have a small, fixed number of unique values.

**The Principal's Take:** The key here is to realize that a general-purpose comparison-based sort (like quicksort or mergesort, which are O(n log n)) is overkill. Because we have a small, fixed number of values, we can use a more efficient, linear-time algorithm like Counting Sort.

**The Code Example (Counting Sort):**
```java
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        // 1. Count the occurrences of each color.
        int[] countArray = new int[3];
        for (int num : nums) {
            countArray[num]++;
        }

        // 2. Overwrite the original array with the sorted values.
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                nums[index++] = i;
            }
        }
    }
}
```

**Detailed Explanation:**
The Counting Sort algorithm works in two passes:
1.  **Counting Pass:** We iterate through the input array and count the number of times each value (0, 1, or 2) appears.
2.  **Overwrite Pass:** We then iterate through our counts, and for each value, we overwrite the original array with that value the number of times it appeared.

This approach has a time complexity of O(n) because we iterate through the array a constant number of times. It's much more efficient than a general-purpose sorting algorithm for this specific problem.

**System Design Insight:**
This problem illustrates an important principle: **always consider the constraints of your data.** When you know more about your data (e.g., the range of values is small), you can often use a specialized algorithm that is much more efficient than a general-purpose one. This is a key aspect of performance optimization.

---

[Previous: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md) | [Next: 06 - Exception Handling: Dealing with the Unexpected](../06-Exception-Handling/README.md)
