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

### Q19: What's the difference between an Array, a Vector, and an ArrayList?

*   **Simple Answer:** An `Array` has a fixed size. An `ArrayList` is a resizable array and is the one you should almost always use. A `Vector` is an old, slow, thread-safe version of `ArrayList` that you should avoid.
*   **Detailed Explanation:**
    *   **Array:** The most basic data structure. Its size is fixed when it's created. To "increase" the size, you have to create a new, larger array and copy the elements over.
    *   **Vector:** A legacy class from Java 1.0. It's a resizable array, but all its methods are `synchronized`, making it thread-safe but slow.
    *   **ArrayList:** The modern replacement for `Vector`. It's also a resizable array, but it's not synchronized, making it much faster.
*   **Key Takeaway:** Use `ArrayList` for your list needs. If you need thread-safety, use `Collections.synchronizedList(new ArrayList<>())` or a dedicated concurrent collection.

### Q20: What's the difference between a Stack and a Queue?

*   **Simple Answer:** A `Stack` is **LIFO** (Last-In, First-Out), like a stack of plates. A `Queue` is **FIFO** (First-In, First-Out), like a line at a store.
*   **Detailed Explanation:**
    *   **Stack:** The last item you `push` (add) is the first item you `pop` (remove). Used for things like reversing a word or implementing undo functionality.
    *   **Queue:** The first item you `add` is the first item you `poll` (remove). Used for processing tasks in the order they were received, like a print queue.
*   **Implementation Note:** Don't use the old `java.util.Stack` class. It's a legacy class. Use an `ArrayDeque` instead to represent a stack. For a queue, `LinkedList` or `ArrayDeque` are good choices.

### Q21: How would you sort an array containing only 0s, 1s, and 2s?

*   **Simple Answer:** Use a "counting sort" algorithm. It's much faster than a general-purpose sort for this specific problem.
*   **The Algorithm (Counting Sort):**
    1.  **Count:** Make a single pass through the array to count the number of 0s, 1s, and 2s.
    2.  **Overwrite:** Make a second pass through the array, filling it up with the number of 0s you counted, then the number of 1s, and finally the number of 2s.
*   **Why it's better:** This algorithm has a time complexity of O(n), because you only pass through the array a constant number of times. A general-purpose sort like `Arrays.sort()` is typically O(n log n), which is slower. This problem shows the importance of using specialized algorithms when you know the constraints of your data.

---

[Previous: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md) | [Next: 06 - Exception Handling: Dealing with the Unexpected](../06-Exception-Handling/README.md)
